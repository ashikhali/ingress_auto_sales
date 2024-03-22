package com.ingress.ingress_auto_sales.controller;

import com.ingress.ingress_auto_sales.model.Car;
import com.ingress.ingress_auto_sales.model.Seller;
import com.ingress.ingress_auto_sales.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sellers")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @PostMapping
    public ResponseEntity<Seller> addCar(@RequestBody Seller seller) {
        Seller addedSeller = sellerService.addCar(seller);
        return new ResponseEntity<>(addedSeller, HttpStatus.CREATED);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Car>> searchCars(@RequestParam(required = false) String mark,
                                                @RequestParam(required = false) String model,
                                                @RequestParam(required = false) String city,
                                                @RequestParam(required = false) Double minPrice,
                                                @RequestParam(required = false) Double maxPrice,
                                                @RequestParam(required = false) String type,
                                                @RequestParam(required = false) Integer minYear,
                                                @RequestParam(required = false) Integer maxYear,
                                                @RequestParam(required = false) Integer minMileage,
                                                @RequestParam(required = false) Integer maxMileage,
                                                @RequestParam(required = false) Integer minSeats,
                                                @RequestParam(required = false) Integer minHorsePower) {
        List<Car> cars = sellerService.searchCars(mark, model, city, minPrice, maxPrice, type, minYear, maxYear, minMileage, maxMileage, minSeats, minHorsePower);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
