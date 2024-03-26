package com.ingress.ingress_auto_sales.config;

import org.mapstruct.factory.Mappers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ingress.ingress_auto_sales.mapper.SellerMapper;

@Configuration
public class SellerMapperConfiguration {

    @Bean
    public SellerMapper sellerMapper() {
        return Mappers.getMapper(SellerMapper.class);
    }
}
