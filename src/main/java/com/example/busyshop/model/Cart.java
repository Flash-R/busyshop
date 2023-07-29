package com.example.busyshop.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    int cartTotal;

    @OneToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();
}
