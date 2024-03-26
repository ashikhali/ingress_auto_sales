package com.ingress.ingress_auto_sales.mapper;

import com.ingress.ingress_auto_sales.dto.CarFeatureRequestDTO;
import com.ingress.ingress_auto_sales.dto.CarFeatureResponseDTO;
import com.ingress.ingress_auto_sales.model.CarFeature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface CarFeatureMapper {

    CarFeatureMapper INSTANCE = Mappers.getMapper(CarFeatureMapper.class);

    @Mapping(target = "car", ignore = true)
    CarFeature toEntity(CarFeatureRequestDTO dto);

    CarFeatureResponseDTO toDto(CarFeature entity);

    List<CarFeatureResponseDTO> toDtoList(List<CarFeature> entities);

    CarFeature fromDto(CarFeatureResponseDTO dto);

    List<CarFeature> fromDtoList(List<CarFeatureResponseDTO> dtos);
}

