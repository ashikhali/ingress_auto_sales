package com.ingress.ingress_auto_sales.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "car")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<CarFeature> features;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "seller_id")
    private Seller seller;

}

