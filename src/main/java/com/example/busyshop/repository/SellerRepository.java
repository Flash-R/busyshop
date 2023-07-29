package com.example.busyshop.repository;

import com.example.busyshop.model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller,Integer> {
    public Seller findByEmail(String email);
}
