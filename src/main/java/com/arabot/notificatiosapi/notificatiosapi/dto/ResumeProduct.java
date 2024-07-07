package com.arabot.notificatiosapi.notificatiosapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class ResumeProduct {

        private UUID productId;
        private String name;
        private int quantity;
        private double totalPrice;

}
