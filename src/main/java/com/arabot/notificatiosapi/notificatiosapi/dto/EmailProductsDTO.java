package com.arabot.notificatiosapi.notificatiosapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class EmailProductsDTO {

    private List<ResumeProduct> resumeProductList;
    private double totalPrice;

}
