package com.example.casemodule4group5.repository;

// tạo interface custom cho category trên giao diện user
public interface IFoodCount {
    Long getCategory_id();

    String getImage();

    String getName();

    Long getTotalFood();
}
