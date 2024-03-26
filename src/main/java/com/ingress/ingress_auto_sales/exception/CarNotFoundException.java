package com.ingress.ingress_auto_sales.exception;

import jakarta.persistence.EntityNotFoundException;

public class CarNotFoundException extends EntityNotFoundException {

    public CarNotFoundException(Long carId) {
        super("Car not found with id: " + carId);
    }
}

