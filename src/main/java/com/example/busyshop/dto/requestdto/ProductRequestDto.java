package com.example.busyshop.dto.requestdto;

import com.example.busyshop.Enum.Category;
import com.example.busyshop.Enum.ProductStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductRequestDto {
    String name;

    Category category;

    int price;

    int quantity;

    String sellerEmail;
}
