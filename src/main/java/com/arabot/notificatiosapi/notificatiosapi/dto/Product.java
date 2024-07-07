package com.arabot.notificatiosapi.notificatiosapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
@Builder
public class Product {

    private UUID id;
    private String name;
    private double price;
    private int stock;
    private String brand;
    private ProductCategory productCategory;
    private Set<ProductAttribute> productAttributes;

}
