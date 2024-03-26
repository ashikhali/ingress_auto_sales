package com.ingress.ingress_auto_sales.mapper;

import com.ingress.ingress_auto_sales.dto.SellerRequestDTO;
import com.ingress.ingress_auto_sales.dto.SellerResponseDTO;
import com.ingress.ingress_auto_sales.model.Seller;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface SellerMapper {

    SellerMapper INSTANCE = Mappers.getMapper(SellerMapper.class);

    Seller toEntity(SellerRequestDTO dto);

    SellerResponseDTO toDto(Seller entity);

    List<SellerResponseDTO> toDtoList(List<Seller> entities);

    Seller fromDto(SellerResponseDTO dto);

    List<Seller> fromDtoList(List<SellerResponseDTO> dtos);
}
