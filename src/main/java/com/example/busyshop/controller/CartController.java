package com.example.busyshop.controller;

import com.example.busyshop.dto.requestdto.CheckoutRequestDto;
import com.example.busyshop.dto.requestdto.ItemRequestDto;
import com.example.busyshop.dto.responsedto.CartResponseDto;
import com.example.busyshop.dto.responsedto.OrderResponseDto;
import com.example.busyshop.model.Item;
import com.example.busyshop.service.CartService;
import com.example.busyshop.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto){
        try{
            Item item =  itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addItemToCart(itemRequestDto, item);
            return new ResponseEntity(cartResponseDto, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    public OrderResponseDto checkoutCart(@RequestBody CheckoutRequestDto checkoutRequestDto){
        OrderResponseDto responseDto = cartService.checkoutCart(checkoutRequestDto);
    }
}
