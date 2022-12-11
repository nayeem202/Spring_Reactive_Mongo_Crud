package com.javaTechie.reactive.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    @Id
    private String id;
    private String name;
    private int qty;
    private double price;



}
