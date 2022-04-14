package com.example.casemodule4group5.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

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
    private String name;

    private String img; // 1 ảnh đại diện món ăn

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private double price;

    private double salePrice;

    private double serviceFee;

    private Date dayCreate;

    private Date dayChange;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "food_tag")
    private Set<Tag> tag;

    private Long countViews;

    private Long countBuys;

    @ManyToOne
    private User user;

    @ManyToOne
    private Category category;

    public Food(Long id, String name, String img, String description, double price, double salePrice, double serviceFee, Date dayCreate, Date dayChange, Long countViews, Long countBuys, User user, Category category) {
        this.id = id;
        this.name = name;
        this.img = img;
        this.description = description;
        this.price = price;
        this.salePrice = salePrice;
        this.serviceFee = serviceFee;
        this.dayCreate = dayCreate;
        this.dayChange = dayChange;
        this.countViews = countViews;
        this.countBuys = countBuys;
        this.user = user;
        this.category = category;
    }
}
