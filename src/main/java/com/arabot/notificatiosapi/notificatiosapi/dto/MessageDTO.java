package com.arabot.notificatiosapi.notificatiosapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class MessageDTO {

    private List<ResumeProduct> productList;

    private double totalAmount;

    private String userEmail;

}
