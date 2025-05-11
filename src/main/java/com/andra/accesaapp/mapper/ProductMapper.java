package com.andra.accesaapp.mapper;

import com.andra.accesaapp.dto.ProductDto;
import com.andra.accesaapp.entity.Product;
import com.andra.accesaapp.model.ProductCsv;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public Product fromCsvToEntity(ProductCsv productCsv) {
        Product product = new Product();
        product.setProductId(productCsv.getProductId());
        product.setProductName(productCsv.getProductName());
        product.setProductCategory(productCsv.getProductCategory());
        product.setBrand(productCsv.getBrand());
        product.setPackageQuantity(productCsv.getPackageQuantity());
        product.setPackageUnit(productCsv.getPackageUnit());
        return product;
    }

    public ProductDto fromEntityToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setProductCategory(product.getProductCategory());
        productDto.setBrand(product.getBrand());
        productDto.setPackageQuantity(product.getPackageQuantity());
        productDto.setPackageUnit(product.getPackageUnit());
        return productDto;
    }

    public Product fromDtoToEntity(ProductDto productDto) {
        Product product = new Product();
        product.setProductId(productDto.getProductId());
        product.setProductName(productDto.getProductName());
        product.setProductCategory(productDto.getProductCategory());
        product.setBrand(productDto.getBrand());
        product.setPackageQuantity(productDto.getPackageQuantity());
        product.setPackageUnit(productDto.getPackageUnit());
        return product;
    }
}
