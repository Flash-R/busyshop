package com.example.busyshop.repository;

import com.example.busyshop.Enum.Category;
import com.example.busyshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("select p from Product p where p.category = :category and p.price >= :price")
    public List<Product> FindByCategoryAndPriceGreater(Category category, int price);
}
