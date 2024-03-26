package com.ingress.ingress_auto_sales.dto;

import lombok.Data;

import java.util.List;

@Data
public class CarRequestDTO {
    private String mark;
    private String model;
    private int productionYear;
    private String color;
    private String city;
    private double price;
    private int mileage;
    private String type;
    private int numberOfSeats;
    private int horsepower;
    private String fuelType;
    private String transmission;
    private double engineSize;
    private String vin;
    private String registrationStatus;
    private String carCondition;
    private String location;
    private List<CarFeatureRequestDTO> features;
    private Long sellerId;
}

