package com.example.busyshop.service;


import com.example.busyshop.dto.requestdto.CustomerRequestDto;
import com.example.busyshop.dto.responsedto.CustomerResponseDto;
import com.example.busyshop.model.Cart;
import com.example.busyshop.model.Customer;
import com.example.busyshop.repository.CustomerRepository;
import com.example.busyshop.transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        //using builders
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cart.setCartTotal(0);

        customer.setCart(cart);

        Customer savedCustomer = customerRepository.save(customer); //saves both the customer and cart

        //prepare the response DTO using builder
        return CustomerTransformer.CustomerToCustomerResponseDto(savedCustomer);

    }
}
