package com.example.busyshop.transformer;


import com.example.busyshop.dto.requestdto.CustomerRequestDto;
import com.example.busyshop.dto.responsedto.CustomerResponseDto;
import com.example.busyshop.model.Customer;
import lombok.experimental.UtilityClass;

//@UtilityClass  //ensures that no objects of this class are made but not a standard practice
public class CustomerTransformer {

    public static Customer CustomerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .gender(customerRequestDto.getGender())
                .email(customerRequestDto.getEmail())
                .phone(customerRequestDto.getPhone())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .gender(customer.getGender())
                .build();
    }
}
