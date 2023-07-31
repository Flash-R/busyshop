package com.example.busyshop.service;

import com.example.busyshop.dto.requestdto.CheckoutRequestDto;
import com.example.busyshop.dto.requestdto.ItemRequestDto;
import com.example.busyshop.dto.responsedto.CartResponseDto;
import com.example.busyshop.dto.responsedto.OrderResponseDto;
import com.example.busyshop.exception.CustomerNotFoundException;
import com.example.busyshop.exception.EmptyCartException;
import com.example.busyshop.exception.InvalidCardException;
import com.example.busyshop.model.*;
import com.example.busyshop.repository.*;
import com.example.busyshop.transformer.CartTransformer;
import com.example.busyshop.transformer.OrderTransformer;
import org.hibernate.metamodel.mapping.ordering.OrderByFragmentTranslator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CartRepository cartRepository;
    public CartResponseDto addItemToCart(ItemRequestDto itemResponseDto, Item item) {
        //get the customer cart
        Customer customer = customerRepository.findByEmail(itemResponseDto.getCustomerEmail());

        //get product
        Product product = productRepository.findById(itemResponseDto.getProductId()).get();

        //get individual cart
        Cart cart = customer.getCart();

        cart.setCartTotal(cart.getCartTotal() + product.getPrice() * itemResponseDto.getRequiredQuantity());
        item.setCart(cart);
        item.setProduct(product);
        // Save the item first to avoid saving duplicates
        Item savedItem = itemRepository.save(item);
        cart.getItems().add(savedItem);
        product.getItemList().add(savedItem);

        Cart savedCart = cartRepository.save(cart);
        productRepository.save(product);

        return CartTransformer.CartToCartResponseDto(savedCart);
    }

    public OrderResponseDto checkoutCart(CheckoutRequestDto checkoutRequestDto) {
        Customer customer = customerRepository.findByEmail(checkoutRequestDto.getCustomerEmail());
        if(customer == null)
            throw new CustomerNotFoundException("Customer not found");
        Card card = cardRepository.findByCardNo(checkoutRequestDto.getCardNo());
        Date today = new Date();
        //check id card is valid
        if(card == null || card.getCvv() != checkoutRequestDto.getCvv() || today.after(card.getValidTill()))
            throw new InvalidCardException("Sorry! Invalid Card");
        Cart cart = customer.getCart();
        if(cart.getItems().size() == 0)
            throw new EmptyCartException("Sorry! your cart is empty");

        OrderEntity order = orderService.placeOrder(cart, card);

        resetCart(cart);
        OrderEntity savedOrder = orderRepository.save(order);

        return OrderTransformer.OrderToOrderResponseDto(savedOrder);
    }

    public void resetCart(Cart cart){
        cart.setCartTotal(0);
        for(Item item : cart.getItems()){
            item.setCart(null);
        }
        cart.setItems(new ArrayList<>());
    }
}
