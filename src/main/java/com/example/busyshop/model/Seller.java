package com.example.busyshop.model;

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
@Table(name = "seller")
@Builder
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String name;

    String phone;

    @Column(unique = true)
    String email;

    String address;

    @OneToMany(mappedBy = "seller",cascade = CascadeType.ALL)
    List<Product> productList = new ArrayList<>();
}
