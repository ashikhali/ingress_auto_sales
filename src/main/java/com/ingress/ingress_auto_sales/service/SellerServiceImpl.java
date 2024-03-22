package com.ingress.ingress_auto_sales.service;

import com.ingress.ingress_auto_sales.model.Car;
import com.ingress.ingress_auto_sales.model.CarFeature;
import com.ingress.ingress_auto_sales.model.Seller;
import com.ingress.ingress_auto_sales.repository.SellerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Seller addCar(Seller seller) {
        return sellerRepository.save(seller);
    }

    @Override
    public List<Car> searchCars(String mark, String model, String city, Double minPrice, Double maxPrice, String type, Integer minYear, Integer maxYear, Integer minMileage, Integer maxMileage, Integer minSeats, Integer minHorsePower) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Car> query = criteriaBuilder.createQuery(Car.class);
        Root<Car> root = query.from(Car.class);

        List<Predicate> predicates = new ArrayList<>();

        if (mark != null && !mark.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("mark"), mark));
        }
        if (model != null && !model.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("model"), model));
        }
        if (city != null && !city.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("city"), city));
        }
        if (minPrice != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("price"), minPrice));
        }
        if (maxPrice != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("price"), maxPrice));
        }
        if (type != null && !type.isEmpty()) {
            predicates.add(criteriaBuilder.equal(root.get("type"), type));
        }
        if (minYear != null && minYear > 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("productionYear"), minYear));
        }
        if (maxYear != null && maxYear > 0) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("productionYear"), maxYear));
        }
        if (minMileage != null && minMileage > 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("mileage"), minMileage));
        }
        if (maxMileage != null && maxMileage > 0) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("mileage"), maxMileage));
        }
        if (minSeats != null && minSeats > 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("numberOfSeats"), minSeats));
        }
        if (minHorsePower != null && minHorsePower > 0) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("horsePower"), minHorsePower));
        }

        query.where(predicates.toArray(new Predicate[0]));

        return entityManager.createQuery(query).getResultList();
    }

}


