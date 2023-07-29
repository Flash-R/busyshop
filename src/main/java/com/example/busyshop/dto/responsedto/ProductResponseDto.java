package com.example.busyshop.dto.responsedto;

import com.example.busyshop.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String name;

    String sellerName;

    int price;

    int quantity;

    ProductStatus status;
}
