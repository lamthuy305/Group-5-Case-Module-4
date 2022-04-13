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

    private String foodName;

    private String img;

    private String description;

    private double price;

    private double salePrice;

    private double serviceFee;

    private String dayCreate;

    private String dayChange;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "food_tag")
    private List<Tag> tag;
}
