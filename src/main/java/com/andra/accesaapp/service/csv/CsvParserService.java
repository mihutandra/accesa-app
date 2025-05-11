package com.andra.accesaapp.service.csv;

import com.andra.accesaapp.mapper.ProductMapper;
import com.andra.accesaapp.model.ProductCsv;
import com.andra.accesaapp.service.ProductService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
@Service
public class CsvParserService {
    private final ProductMapper productMapper;
    private final ProductService productService;

    private static final String CSV_FILE_PATH = "src/main/resources/static/";

    public CsvParserService(ProductMapper productMapper, ProductService productService) {
        this.productMapper = productMapper;
        this.productService = productService;
    }
    @Transactional
    public void parseProductsAndTheirPriceHistoryFromFile(String fileName) {
        try(Reader reader = new FileReader(CSV_FILE_PATH + fileName)) {
            CsvToBean<ProductCsv> csvToBean = new CsvToBeanBuilder<ProductCsv>(reader)
                    .withType(ProductCsv.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .withIgnoreQuotations(true)
                    .withSeparator(';')
                    .build();
            List<ProductCsv> products = csvToBean.parse();
            products.stream()
                    .map(productMapper::fromCsvToEntity)
                    .forEach(product -> {
                        productService.createProduct(productMapper.fromEntityToDto(product));
                    });

        }catch (IOException e) {
            System.out.println("Error reading the CSV file: " + e.getMessage());
        }
    }
}