package com.example.busyshop.dto.responsedto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OrderResponseDto {
    String orderId;

    Date orderDate;

    String customerName;

    String cardUsed;

    int orderTotal;

    List<ItemResponseDto> items;
}
