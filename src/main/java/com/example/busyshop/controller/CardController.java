package com.example.busyshop.controller;

import com.example.busyshop.dto.requestdto.CardRequestDto;
import com.example.busyshop.dto.responsedto.CardResponseDto;
import com.example.busyshop.exception.CustomerNotFoundException;
import com.example.busyshop.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("card")
public class CardController {
    @Autowired
    CardService cardService;
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        try{
            CardResponseDto responseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(responseDto, HttpStatus.CREATED);
        }catch (CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }


    }
}
