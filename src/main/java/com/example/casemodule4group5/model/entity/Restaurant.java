package com.example.casemodule4group5.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Restaurant { // Cửa hàng
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String img;

    @Column(nullable = false)
    private String address;

    private String openTime;

    private String closeTime;

    public Restaurant(String name, String img, String address, String openTime, String closeTime) {
        this.name = name;
        this.img = img;
        this.address = address;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }
}
