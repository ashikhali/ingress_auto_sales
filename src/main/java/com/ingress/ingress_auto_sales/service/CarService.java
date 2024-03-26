package com.ingress.ingress_auto_sales.service;

import com.ingress.ingress_auto_sales.dto.CarRequestDTO;
import com.ingress.ingress_auto_sales.dto.CarResponseDTO;

import java.util.List;

public interface CarService {

    CarResponseDTO getCarById(Long id);

    List<CarResponseDTO> getAllCars();

    CarResponseDTO addCar(CarRequestDTO carRequestDTO);

    CarResponseDTO updateCar(Long id, CarRequestDTO carRequestDTO);

    void deleteCar(Long id);
}

