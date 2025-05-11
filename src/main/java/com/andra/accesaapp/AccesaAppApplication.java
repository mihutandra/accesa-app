package com.andra.accesaapp;

import com.andra.accesaapp.service.csv.CsvParserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AccesaAppApplication implements CommandLineRunner{
    @Autowired
    private CsvParserService csvParserService;
    public static void main(String[] args) {
        SpringApplication.run(AccesaAppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        csvParserService.parseProductsAndTheirPriceHistoryFromFile("profi_2025-05-08.csv");
    }
}
