package com.example.busyshop.service;

import com.example.busyshop.dto.requestdto.SellerRequestDto;
import com.example.busyshop.dto.responsedto.SellerResponseDto;
import com.example.busyshop.model.Seller;
import com.example.busyshop.repository.SellerRepository;
import com.example.busyshop.transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerService {
    @Autowired
    SellerRepository sellerRepository;
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
        //Request DTo to Seller
        Seller seller = SellerTransformer.SellerRequestDtoToSeller(sellerRequestDto);

        //Saving the Seller
        Seller savedSeller = sellerRepository.save(seller);

        //seller to responseDto
        return SellerTransformer.SellerToSellerResponseDto(savedSeller);
    }
}
