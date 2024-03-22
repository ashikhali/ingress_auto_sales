package com.ingress.ingress_auto_sales.repository;

import com.ingress.ingress_auto_sales.model.CarFeature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarFeatureRepository extends JpaRepository<CarFeature, Long> {

}

