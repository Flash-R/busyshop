package com.example.busyshop.repository;

import com.example.busyshop.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    //find by customer email
    public Customer findByEmail(String email);
}
