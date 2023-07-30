package com.example.busyshop.repository;

import com.example.busyshop.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    public Card findByCardNo(String cardNo);
}
