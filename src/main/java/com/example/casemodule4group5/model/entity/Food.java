package com.example.casemodule4group5.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "foods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private double price;

    private String img;

    private double salePrice;

    private double serviceFee;

    private String dayCreate;

    private String dayChange;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "food_tag")
    private List<Tag> tag;

    public Food(Long id, String name, double price, String img, double salePrice, double serviceFee, String dayCreate, String dayChange) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.img = img;
        this.salePrice = salePrice;
        this.serviceFee = serviceFee;
        this.dayCreate = dayCreate;
        this.dayChange = dayChange;
    }
}
