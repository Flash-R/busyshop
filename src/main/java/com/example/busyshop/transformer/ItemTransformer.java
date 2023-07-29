package com.example.busyshop.transformer;

import com.example.busyshop.dto.requestdto.ItemRequestDto;
import com.example.busyshop.dto.responsedto.ItemResponseDto;
import com.example.busyshop.model.Item;
import com.example.busyshop.repository.ItemRepository;

public class ItemTransformer {
    public static Item ItemRequestDtoToItem(int requiredQuantity){
        return Item.builder()
                .requiredQuantity(requiredQuantity)
                .build();
    }

    public static ItemResponseDto ItemToItemresponseDto(Item item){
        return ItemResponseDto.builder()
                .itemName(item.getProduct().getName())
                .itemPrice(item.getProduct().getPrice())
                .productCategory(item.getProduct().getCategory())
                .quantityAdded(item.getRequiredQuantity())
                .build();
    }
}
