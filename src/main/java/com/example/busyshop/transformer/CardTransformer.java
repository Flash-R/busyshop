package com.example.busyshop.transformer;

import com.example.busyshop.dto.requestdto.CardRequestDto;
import com.example.busyshop.dto.responsedto.CardResponseDto;
import com.example.busyshop.model.Card;

public class CardTransformer {
    public static Card CardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .validTill(cardRequestDto.getValidTill())
                .cvv(cardRequestDto.getCvv())
                .build();
    }
    public static CardResponseDto CardToCArdResponseDto(Card card){
        return CardResponseDto.builder()
                .cardType(card.getCardType())
                .customerEmail(card.getCustomer().getEmail())
                .validTill(card.getValidTill())
                .build();
    }
}
