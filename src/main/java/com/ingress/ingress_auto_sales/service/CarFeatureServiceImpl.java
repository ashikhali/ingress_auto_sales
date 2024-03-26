package com.ingress.ingress_auto_sales.service;

import com.ingress.ingress_auto_sales.dto.CarFeatureRequestDTO;
import com.ingress.ingress_auto_sales.dto.CarFeatureResponseDTO;
import com.ingress.ingress_auto_sales.exception.CarFeatureNotFoundException;
import com.ingress.ingress_auto_sales.mapper.CarFeatureMapper;
import com.ingress.ingress_auto_sales.model.CarFeature;
import com.ingress.ingress_auto_sales.repository.CarFeatureRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarFeatureServiceImpl implements CarFeatureService {

    @Autowired
    private CarFeatureRepository carFeatureRepository;

    @Autowired
    private CarFeatureMapper carFeatureMapper;

    @Override
    public CarFeatureResponseDTO getCarFeatureById(Long id) {
        Optional<CarFeature> optionalCarFeature = carFeatureRepository.findById(id);
        if (optionalCarFeature.isPresent()) {
            return carFeatureMapper.toDto(optionalCarFeature.get());
        } else {
            throw new CarFeatureNotFoundException(id);
        }
    }

    @Override
    public List<CarFeatureResponseDTO> getAllCarFeatures() {
        List<CarFeature> carFeatures = carFeatureRepository.findAll();
        return carFeatureMapper.toDtoList(carFeatures);
    }

    @Override
    @Transactional
    public CarFeatureResponseDTO addCarFeature(CarFeatureRequestDTO carFeatureRequestDTO) {
        CarFeature carFeature = carFeatureMapper.toEntity(carFeatureRequestDTO);
        carFeatureRepository.save(carFeature);
        return carFeatureMapper.toDto(carFeature);
    }

    @Override
    @Transactional
    public CarFeatureResponseDTO updateCarFeature(Long id, CarFeatureRequestDTO carFeatureRequestDTO) {
        Optional<CarFeature> optionalCarFeature = carFeatureRepository.findById(id);
        if (optionalCarFeature.isPresent()) {
            CarFeature carFeature = optionalCarFeature.get();
            BeanUtils.copyProperties(carFeatureRequestDTO, carFeature);
            return carFeatureMapper.toDto(carFeature);
        } else {
            throw new CarFeatureNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void deleteCarFeature(Long id) {
        Optional<CarFeature> optionalCarFeature = carFeatureRepository.findById(id);
        if (optionalCarFeature.isPresent()) {
            carFeatureRepository.deleteById(id);
        } else {
            throw new CarFeatureNotFoundException(id);
        }
    }
}

