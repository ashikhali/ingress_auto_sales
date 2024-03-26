package com.ingress.ingress_auto_sales.controller;

import com.ingress.ingress_auto_sales.model.Car;
import com.ingress.ingress_auto_sales.model.Seller;
import com.ingress.ingress_auto_sales.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public ResponseEntity<Seller> addSeller(@RequestBody Seller seller) {
        Seller addedSeller = sellerService.addSeller(seller);
        return new ResponseEntity<>(addedSeller, HttpStatus.CREATED);
    }

    @PostMapping("/{sellerId}/cars")
    public ResponseEntity<Car> addCarToSeller(@PathVariable Long sellerId, @RequestBody Car car) {
        Car addedCar = sellerService.addCarToSeller(sellerId, car);
        return new ResponseEntity<>(addedCar, HttpStatus.CREATED);
    }

    @DeleteMapping("/{sellerId}/cars/{carId}")
    public ResponseEntity<Void> removeCarFromSeller(@PathVariable Long sellerId, @PathVariable Long carId) {
        sellerService.removeCarFromSeller(sellerId, carId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<Page<Car>> searchCars(@RequestParam(required = false) String mark,
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
                                                @RequestParam(required = false) Integer minHorsePower,
                                                @RequestParam(defaultValue = "0") int page,
                                                @RequestParam(defaultValue = "10") int size,
                                                @RequestParam(defaultValue = "price") String sortBy,
                                                @RequestParam(defaultValue = "asc") String sortDirection) {
        // Create a Pageable object with page number, size, and sorting
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortDirection), sortBy));

        // Call the service method with the pageable object
        Page<Car> carPage = sellerService.searchCars(mark, model, city, minPrice, maxPrice, type, minYear, maxYear, minMileage, maxMileage, minSeats, minHorsePower, pageable);

        // Return the page of cars along with HTTP status
        return new ResponseEntity<>(carPage, HttpStatus.OK);
    }
}
