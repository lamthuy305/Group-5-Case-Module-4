package com.example.casemodule4group5.model.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "carts")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    @OneToOne
    private Food food;

    private double quantity;

    public Cart(Order order, Food food, double quantity) {
        this.order = order;
        this.food = food;
        this.quantity = quantity;
    }
}
