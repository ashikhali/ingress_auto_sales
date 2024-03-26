package com.ingress.ingress_auto_sales.service;

import com.ingress.ingress_auto_sales.dto.CarFeatureRequestDTO;
import com.ingress.ingress_auto_sales.dto.CarFeatureResponseDTO;

import java.util.List;

public interface CarFeatureService {

    CarFeatureResponseDTO getCarFeatureById(Long id);

    List<CarFeatureResponseDTO> getAllCarFeatures();

    CarFeatureResponseDTO addCarFeature(CarFeatureRequestDTO carFeatureRequestDTO);

    CarFeatureResponseDTO updateCarFeature(Long id, CarFeatureRequestDTO carFeatureRequestDTO);

    void deleteCarFeature(Long id);
}

