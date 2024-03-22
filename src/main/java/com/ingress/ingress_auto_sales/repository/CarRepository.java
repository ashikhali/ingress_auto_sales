package com.ingress.ingress_auto_sales.repository;

import com.ingress.ingress_auto_sales.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

}

