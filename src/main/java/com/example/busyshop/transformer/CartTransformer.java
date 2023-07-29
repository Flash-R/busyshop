package com.example.busyshop.transformer;

import com.example.busyshop.dto.responsedto.CartResponseDto;
import com.example.busyshop.dto.responsedto.ItemResponseDto;
import com.example.busyshop.model.Cart;
import com.example.busyshop.model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {

    public static CartResponseDto CartToCartResponseDto(Cart cart){
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item : cart.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemresponseDto(item));
        }
        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtoList)
                .build();
    }
}
