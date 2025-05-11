package com.andra.accesaapp.dto;

import lombok.Data;

@Data
public class ProductDto {
    private String productId;
    private String productName;
    private String productCategory;
    private String brand;
    private double packageQuantity;
    private String packageUnit;

}