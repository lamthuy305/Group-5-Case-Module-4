package com.example.casemodule4group5.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
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

    @Column(nullable = false)
    private String foodName;

    private String img;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    private double salePrice;

    private double serviceFee;

    private String dayCreate;

    private String dayChange;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "food_tag")
    private List<Tag> tag;
}
