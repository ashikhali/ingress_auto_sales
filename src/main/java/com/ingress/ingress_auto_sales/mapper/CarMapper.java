package com.ingress.ingress_auto_sales.mapper;

import com.ingress.ingress_auto_sales.dto.CarRequestDTO;
import com.ingress.ingress_auto_sales.dto.CarResponseDTO;
import com.ingress.ingress_auto_sales.model.Car;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface CarMapper {

    CarMapper INSTANCE = Mappers.getMapper(CarMapper.class);

    Car toEntity(CarRequestDTO dto);

    CarResponseDTO toDto(Car entity);

    List<CarResponseDTO> toDtoList(List<Car> entities);

    Car fromDto(CarResponseDTO dto);

    List<Car> fromDtoList(List<CarResponseDTO> dtos);
}

