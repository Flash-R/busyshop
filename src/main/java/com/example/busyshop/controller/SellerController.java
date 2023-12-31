package com.example.busyshop.controller;

import com.example.busyshop.dto.requestdto.SellerRequestDto;
import com.example.busyshop.dto.responsedto.SellerResponseDto;
import com.example.busyshop.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerService sellerService;


    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto response = sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity(response, HttpStatus.CREATED);
    }
}
