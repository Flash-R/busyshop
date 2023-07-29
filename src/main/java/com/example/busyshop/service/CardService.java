package com.example.busyshop.service;

import com.example.busyshop.dto.requestdto.CardRequestDto;
import com.example.busyshop.dto.responsedto.CardResponseDto;
import com.example.busyshop.exception.CustomerNotFoundException;
import com.example.busyshop.model.Card;
import com.example.busyshop.model.Customer;
import com.example.busyshop.repository.CustomerRepository;
import com.example.busyshop.transformer.CardTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {
    @Autowired
    CustomerRepository customerRepository;
    public CardResponseDto addCard(CardRequestDto cardRequestDto) {
        //Check if the customer exists
        Customer customer = customerRepository.findByEmail(cardRequestDto.getCustomerEmail());
        if(customer == null)
            throw new CustomerNotFoundException("Invalid Customer Id");
        //Converting request dto to card Entity
        Card card = CardTransformer.CardRequestDtoToCard(cardRequestDto);
        card.setCustomer(customer);
        customer.getCards().add(card);

        //save the card by saving the parent customer 
        Customer savedCustomer = customerRepository.save(customer);
        //get the latestCard
        Card savedCard = savedCustomer.getCards().get(savedCustomer.getCards().size() -1);

        //Changing the card to ResponseDto

        CardResponseDto cardResponseDto = CardTransformer.CardToCArdResponseDto(savedCard);
        cardResponseDto.setCardNo(hideCardNumber(savedCard.getCardNo()));

        return cardResponseDto;
    }

    public String hideCardNumber(String cardNo){
        String card = "";
        int len = cardNo.length();
        for(int i=0; i<len-4; i++){
            card += "X";
        }
        card += cardNo.substring(len - 4);
        return card;
    }
}
