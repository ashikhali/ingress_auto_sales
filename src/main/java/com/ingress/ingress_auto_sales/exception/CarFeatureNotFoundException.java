package com.ingress.ingress_auto_sales.exception;

import jakarta.persistence.EntityNotFoundException;

public class CarFeatureNotFoundException extends EntityNotFoundException {

    public CarFeatureNotFoundException(Long id) {
        super("Car Feature not found with id: " + id);
    }
}

