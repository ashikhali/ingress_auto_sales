package com.ingress.ingress_auto_sales.service;

import com.ingress.ingress_auto_sales.model.Car;
import com.ingress.ingress_auto_sales.model.Seller;

import java.util.List;

public interface SellerService {
    Seller addCar(Seller seller);
    List<Car> searchCars(String mark, String model, String city, Double minPrice, Double maxPrice, String type, Integer minYear, Integer maxYear, Integer minMileage, Integer maxMileage, Integer minSeats, Integer minHorsePower);
}

