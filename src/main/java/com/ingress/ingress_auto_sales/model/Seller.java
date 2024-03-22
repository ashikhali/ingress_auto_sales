package com.ingress.ingress_auto_sales.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;
    private String phone;

    @OneToMany(mappedBy = "seller", cascade = CascadeType.ALL)
    private List<Car> cars;
}

