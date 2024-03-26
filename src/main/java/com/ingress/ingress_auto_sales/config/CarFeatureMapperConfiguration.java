package com.ingress.ingress_auto_sales.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ingress.ingress_auto_sales.mapper.CarFeatureMapper;

@Configuration
public class CarFeatureMapperConfiguration {

    @Bean
    public CarFeatureMapper carFeatureMapper() {
        return Mappers.getMapper(CarFeatureMapper.class);
    }
}

