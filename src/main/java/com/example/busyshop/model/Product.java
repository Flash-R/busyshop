package com.example.busyshop.model;


import com.example.busyshop.Enum.Category;
import com.example.busyshop.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    @Enumerated(EnumType.STRING)
    Category category;

    int price;

    int quantity;

    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Item> itemList = new ArrayList<>();

    @ManyToOne
    @JoinColumn
    Seller seller;

}
