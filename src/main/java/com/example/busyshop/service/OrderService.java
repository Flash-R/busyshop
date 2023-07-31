package com.example.busyshop.service;

import com.example.busyshop.Enum.ProductStatus;
import com.example.busyshop.dto.requestdto.OrderRequestDto;
import com.example.busyshop.dto.responsedto.OrderResponseDto;
import com.example.busyshop.exception.CustomerNotFoundException;
import com.example.busyshop.exception.InsufficientProductAmountException;
import com.example.busyshop.exception.InvalidCardException;
import com.example.busyshop.exception.ProductNotFoundException;
import com.example.busyshop.model.*;
import com.example.busyshop.repository.CardRepository;
import com.example.busyshop.repository.CustomerRepository;
import com.example.busyshop.repository.OrderRepository;
import com.example.busyshop.repository.ProductRepository;
import com.example.busyshop.transformer.ItemTransformer;
import com.example.busyshop.transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CardRepository cardRepository;
    @Autowired
    CardService cardService;
    @Autowired
    OrderRepository orderRepository;
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) {
        //check if customer exists
        Customer customer = customerRepository.findByEmail(orderRequestDto.getCustomerEmail());
        if(customer == null)
            throw new CustomerNotFoundException("invalid Customer email");
        //check if product is valid
        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());
        if(optionalProduct.isEmpty())
            throw new ProductNotFoundException("Invalid Product");

        Product product = optionalProduct.get();

        // check for card validity
        Date today = new Date();
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardUsed());
        if(card == null || card.getCvv() != orderRequestDto.getCvv() || today.after(card.getValidTill()))
            throw new InvalidCardException("Invalid Card");

        //check quantity
        if(product.getQuantity() < orderRequestDto.getRequiredQuantity())
            throw new InsufficientProductAmountException("Insufficient Products");

        int newQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();

        product.setQuantity(newQuantity);

        if(product.getQuantity() == 0)
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);

        //converting the orderRequestDto to OrderEntity
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(cardService.hideCardNumber(orderRequestDto.getCardUsed()));
        orderEntity.setOrderTotal(product.getPrice() * orderRequestDto.getRequiredQuantity());
        orderEntity.setCustomer(customer);

        //create the item
        Item item = ItemTransformer.ItemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);
        item.setOrderEntity(orderEntity);

        orderEntity.getItems().add(item);

        OrderEntity savedOrder = orderRepository.save(orderEntity);

        product.getItemList().add(savedOrder.getItems().get(0));

        customer.getOrders().add(savedOrder);

//        productRepository.save(product);
//        customerRepository.save(customer);

        //prepare the ResponseDto

        return OrderTransformer.OrderToOrderResponseDto(savedOrder);



    }

    public OrderEntity placeOrder(Cart cart, Card card) {
        OrderEntity order = new OrderEntity();
        order.setOrderId(String.valueOf(UUID.randomUUID()));
        order.setCardUsed(cardService.hideCardNumber(card.getCardNo()));

        int orderTotal = 0;

        for(Item item : cart.getItems()){
            Product product = item.getProduct();

            if(product.getQuantity() < item.getRequiredQuantity())
                throw new InsufficientProductAmountException("insufficient product amount");

            int newQuantity = product.getQuantity() - item.getRequiredQuantity();
            product.setQuantity(newQuantity);

            if(product.getQuantity() == 0)
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);
            orderTotal += product.getPrice() * item.getRequiredQuantity();

            item.setOrderEntity(order);
        }
        order.setOrderTotal(orderTotal);
        order.setItems(cart.getItems());
        order.setCustomer(card.getCustomer());

        return order;
    }
}
