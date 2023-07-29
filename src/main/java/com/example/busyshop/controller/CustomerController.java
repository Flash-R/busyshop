package com.example.busyshop.controller;


import com.example.busyshop.dto.requestdto.CustomerRequestDto;
import com.example.busyshop.dto.responsedto.CustomerResponseDto;
import com.example.busyshop.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto response = customerService.addCustomer(customerRequestDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
