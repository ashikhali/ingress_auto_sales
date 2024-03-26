package com.ingress.ingress_auto_sales.dto;

import lombok.Data;

import java.util.List;

@Data
public class SellerResponseDTO {
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private List<CarResponseDTO> cars;
}

