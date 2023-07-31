package com.example.busyshop.transformer;

import com.example.busyshop.dto.requestdto.OrderRequestDto;
import com.example.busyshop.dto.responsedto.ItemResponseDto;
import com.example.busyshop.dto.responsedto.OrderResponseDto;
import com.example.busyshop.model.Item;
import com.example.busyshop.model.OrderEntity;
import com.example.busyshop.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {

    public static OrderResponseDto OrderToOrderResponseDto(OrderEntity orderEntity){
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item : orderEntity.getItems()){
            itemResponseDtoList.add(ItemTransformer.ItemToItemresponseDto(item));
        }
        return OrderResponseDto.builder()
                .customerName(orderEntity.getCustomer().getName())
                .orderId(orderEntity.getOrderId())
                .orderTotal(orderEntity.getOrderTotal())
                .cardUsed(orderEntity.getCardUsed())
                .orderDate(orderEntity.getOrderDate())
                .items(itemResponseDtoList)
                .build();
    }
}
