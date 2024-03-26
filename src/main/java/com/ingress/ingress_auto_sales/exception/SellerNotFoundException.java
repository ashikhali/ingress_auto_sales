package com.ingress.ingress_auto_sales.exception;

import jakarta.persistence.EntityNotFoundException;

public class SellerNotFoundException extends EntityNotFoundException {

    public SellerNotFoundException(Long sellerId) {
        super("Seller not found with id: " + sellerId);
    }
}

