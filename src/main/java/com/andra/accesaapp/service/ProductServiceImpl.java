package com.andra.accesaapp.service;
import com.andra.accesaapp.dto.ProductDto;
import com.andra.accesaapp.entity.Product;
import com.andra.accesaapp.repository.ProductRepo;
import com.andra.accesaapp.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepo productRepo;
    private final ProductMapper productMapper;

    public ProductServiceImpl(ProductRepo productRepo, ProductMapper productMapper) {
        this.productRepo = productRepo;
        this.productMapper = productMapper;
    }


    @Override
    public List<ProductDto> getAllProducts() {
        return productRepo.findAll().stream()
                .map(productMapper::fromEntityToDto)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDto> getProductById(String id) {
        return productRepo.findById(id).map(productMapper::fromEntityToDto);
    }

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = productMapper.fromDtoToEntity(productDto);
        Product savedProduct = productRepo.save(product);
        return convertToDto(savedProduct);
    }

    @Override
    public ProductDto updateProduct(String id, ProductDto productDto) {
        Product existingProduct = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        existingProduct.setProductCategory(productDto.getProductCategory());
        existingProduct.setProductName(productDto.getProductName());
        existingProduct.setBrand(productDto.getBrand());
        existingProduct.setPackageQuantity(productDto.getPackageQuantity());
        existingProduct.setPackageUnit(productDto.getPackageUnit());


        Product updatedProduct = productRepo.save(existingProduct);
        return convertToDto(updatedProduct);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        productRepo.delete(product);
    }

    private ProductDto convertToDto(Product product) {
        return productMapper.fromEntityToDto(product);

    }
}

