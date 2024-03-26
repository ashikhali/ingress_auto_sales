package com.ingress.ingress_auto_sales.controller;

import com.ingress.ingress_auto_sales.dto.CarFeatureRequestDTO;
import com.ingress.ingress_auto_sales.dto.CarFeatureResponseDTO;
import com.ingress.ingress_auto_sales.service.CarFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carFeatures")
public class CarFeatureController {

    @Autowired
    private CarFeatureService carFeatureService;

    @GetMapping("/{id}")
    public ResponseEntity<CarFeatureResponseDTO> getCarFeatureById(@PathVariable Long id) {
        CarFeatureResponseDTO carFeature = carFeatureService.getCarFeatureById(id);
        return ResponseEntity.ok(carFeature);
    }

    @GetMapping
    public ResponseEntity<List<CarFeatureResponseDTO>> getAllCarFeatures() {
        List<CarFeatureResponseDTO> carFeatures = carFeatureService.getAllCarFeatures();
        return ResponseEntity.ok(carFeatures);
    }

    @PostMapping
    public ResponseEntity<CarFeatureResponseDTO> addCarFeature(@RequestBody CarFeatureRequestDTO carFeatureRequestDTO) {
        CarFeatureResponseDTO addedCarFeature = carFeatureService.addCarFeature(carFeatureRequestDTO);
        return new ResponseEntity<>(addedCarFeature, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarFeatureResponseDTO> updateCarFeature(@PathVariable Long id, @RequestBody CarFeatureRequestDTO carFeatureRequestDTO) {
        CarFeatureResponseDTO updatedCarFeature = carFeatureService.updateCarFeature(id, carFeatureRequestDTO);
        return ResponseEntity.ok(updatedCarFeature);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCarFeature(@PathVariable Long id) {
        carFeatureService.deleteCarFeature(id);
        return ResponseEntity.noContent().build();
    }
}

