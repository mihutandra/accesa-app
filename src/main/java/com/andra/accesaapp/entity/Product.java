package com.andra.accesaapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "products")
@Data
public class Product {

    @Id
    private String productId;

    private String productName;

    private String productCategory;

    private String brand;

    private double packageQuantity;

    private String packageUnit;

}