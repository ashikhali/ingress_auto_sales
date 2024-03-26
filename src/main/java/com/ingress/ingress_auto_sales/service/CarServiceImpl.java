package com.ingress.ingress_auto_sales.service;

import com.ingress.ingress_auto_sales.dto.CarRequestDTO;
import com.ingress.ingress_auto_sales.dto.CarResponseDTO;
import com.ingress.ingress_auto_sales.exception.CarNotFoundException;
import com.ingress.ingress_auto_sales.mapper.CarMapper;
import com.ingress.ingress_auto_sales.model.Car;
import com.ingress.ingress_auto_sales.repository.CarRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private final CarRepository carRepository;

    @Autowired
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public CarResponseDTO getCarById(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            return carMapper.toDto(optionalCar.get());
        } else {
            throw new CarNotFoundException(id);
        }
    }

    @Override
    public List<CarResponseDTO> getAllCars() {
        List<Car> cars = carRepository.findAll();
        return carMapper.toDtoList(cars);
    }

    @Override
    @Transactional
    public CarResponseDTO addCar(CarRequestDTO carRequestDTO) {
        Car car = carMapper.toEntity(carRequestDTO);
        carRepository.save(car);
        return carMapper.toDto(car);
    }

    @Override
    @Transactional
    public CarResponseDTO updateCar(Long id, CarRequestDTO carRequestDTO) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            // Update car properties from the DTO manually
            BeanUtils.copyProperties(carRequestDTO, car);
            return carMapper.toDto(car);
        } else {
            throw new CarNotFoundException(id);
        }
    }

    @Override
    @Transactional
    public void deleteCar(Long id) {
        Optional<Car> optionalCar = carRepository.findById(id);
        if (optionalCar.isPresent()) {
            carRepository.deleteById(id);
        } else {
            throw new CarNotFoundException(id);
        }
    }
}
