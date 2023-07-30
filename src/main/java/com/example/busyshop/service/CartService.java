package com.example.busyshop.service;

import com.example.busyshop.dto.requestdto.ItemRequestDto;
import com.example.busyshop.dto.responsedto.CartResponseDto;
import com.example.busyshop.model.Cart;
import com.example.busyshop.model.Customer;
import com.example.busyshop.model.Item;
import com.example.busyshop.model.Product;
import com.example.busyshop.repository.CartRepository;
import com.example.busyshop.repository.CustomerRepository;
import com.example.busyshop.repository.ItemRepository;
import com.example.busyshop.repository.ProductRepository;
import com.example.busyshop.transformer.CartTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ItemRepository itemRepository;

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
}
