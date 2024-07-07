package com.arabot.notificatiosapi.notificatiosapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class MailProductsDTO {

    private List<ResumeProduct> resumeProductList;
    private double totalPrice;

}
