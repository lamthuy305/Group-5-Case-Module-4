package com.example.casemodule4group5.model.dto;

import com.example.casemodule4group5.model.entity.Food;
import com.example.casemodule4group5.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "cart_form")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartForm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @OneToOne
    private Food food;

    private double quantity;

}
