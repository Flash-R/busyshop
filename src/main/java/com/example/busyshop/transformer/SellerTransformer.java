package com.example.busyshop.transformer;

import com.example.busyshop.dto.requestdto.SellerRequestDto;
import com.example.busyshop.dto.responsedto.SellerResponseDto;
import com.example.busyshop.model.Seller;

public class SellerTransformer {
    public static Seller SellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .email(sellerRequestDto.getEmail())
                .phone(sellerRequestDto.getPhone())
                .address(sellerRequestDto.getAddress())
                .build();
    }

    public static SellerResponseDto SellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .email(seller.getEmail())
                .build();
    }
}
