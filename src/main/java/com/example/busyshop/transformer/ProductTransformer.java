package com.example.busyshop.transformer;

import com.example.busyshop.Enum.ProductStatus;
import com.example.busyshop.dto.requestdto.ProductRequestDto;
import com.example.busyshop.dto.responsedto.ProductResponseDto;
import com.example.busyshop.model.Product;
import com.example.busyshop.model.Seller;

public class ProductTransformer {
    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getName())
                .category(productRequestDto.getCategory())
                .quantity(productRequestDto.getQuantity())
                .price(productRequestDto.getPrice())
                .productStatus(ProductStatus.AVAILABLE)
                .build();
    }

    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                //.sellerName(product.getSeller().getName())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .status(product.getProductStatus())
                .build();
    }
}
