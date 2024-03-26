package com.ingress.ingress_auto_sales.service;

import com.ingress.ingress_auto_sales.dto.CarRequestDTO;
import com.ingress.ingress_auto_sales.dto.CarResponseDTO;
import com.ingress.ingress_auto_sales.dto.SellerResponseDTO;
import com.ingress.ingress_auto_sales.model.Car;
import com.ingress.ingress_auto_sales.model.Seller;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SellerService {

    SellerResponseDTO getSellerById(Long id);

    Seller addSeller(Seller seller);

    Car addCarToSeller(Long sellerId, Car car);

    void removeCarFromSeller(Long sellerId, Long carId);
    Page<Car> searchCars(String mark, String model, String city, Double minPrice, Double maxPrice, String type, Integer minYear, Integer maxYear, Integer minMileage, Integer maxMileage, Integer minSeats, Integer minHorsePower, Pageable pageable);
}

