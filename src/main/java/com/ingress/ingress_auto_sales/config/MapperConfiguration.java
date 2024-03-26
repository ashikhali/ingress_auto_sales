package com.ingress.ingress_auto_sales.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ingress.ingress_auto_sales.mapper.CarMapper;

@Configuration
public class MapperConfiguration {

    @Bean
    public CarMapper carMapper() {
        return Mappers.getMapper(CarMapper.class);
    }
}

