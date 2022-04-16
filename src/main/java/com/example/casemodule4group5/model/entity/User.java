package com.example.casemodule4group5.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.Set;


@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "Varchar(50)",nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "Varchar(10)",nullable = false,unique = true)
    private String phoneNumber;

    @OneToOne
    private Restaurant restaurant;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role")
    private Set<Role> roles;


    public User(String name, String email, String password, Restaurant restaurant, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.restaurant = restaurant;
        this.roles = roles;
    }

    public User(String name, String email, String password, Set<Role> roles) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
