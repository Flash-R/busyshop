package com.example.busyshop.service;

import com.example.busyshop.dto.requestdto.ItemRequestDto;
import com.example.busyshop.exception.CustomerNotFoundException;
import com.example.busyshop.exception.InsufficientProductAmountException;
import com.example.busyshop.exception.ProductNotFoundException;
import com.example.busyshop.model.Customer;
import com.example.busyshop.model.Item;
import com.example.busyshop.model.Product;
import com.example.busyshop.repository.CustomerRepository;
import com.example.busyshop.repository.ProductRepository;
import com.example.busyshop.transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    public Item createItem(ItemRequestDto itemRequestDto) {
        //check if the customer exists
        Customer customer = customerRepository.findByEmail(itemRequestDto.getCustomerEmail());
        if(customer == null)
            throw new CustomerNotFoundException("Invalid Customer Email");
        // check if the product is correct
        Optional<Product> product = productRepository.findById(itemRequestDto.getProductId());
        if(product.isEmpty())
            throw new ProductNotFoundException("Sorry: Invalid Product Id");

        Product foundProduct = product.get();

        //check if the required amount is available in the stock

        if(foundProduct.getQuantity() < itemRequestDto.getRequiredQuantity())
            throw new InsufficientProductAmountException("Sorry: We do not have the required amount");

        //create item from RequestDto
        Item item = ItemTransformer.ItemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        item.setProduct(foundProduct);

        return item;
    }
}
