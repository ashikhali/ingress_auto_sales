package com.ingress.ingress_auto_sales.service;

import com.ingress.ingress_auto_sales.dto.CarResponseDTO;
import com.ingress.ingress_auto_sales.dto.SellerRequestDTO;
import com.ingress.ingress_auto_sales.dto.SellerResponseDTO;
import com.ingress.ingress_auto_sales.exception.CarNotFoundException;
import com.ingress.ingress_auto_sales.exception.SellerNotFoundException;
import com.ingress.ingress_auto_sales.mapper.CarMapper;
import com.ingress.ingress_auto_sales.mapper.SellerMapper;
import com.ingress.ingress_auto_sales.model.Car;
import com.ingress.ingress_auto_sales.model.Seller;
import com.ingress.ingress_auto_sales.repository.CarRepository;
import com.ingress.ingress_auto_sales.repository.SellerRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    @Autowired
    private final SellerRepository sellerRepository;

    @Autowired
    private final CarRepository carRepository;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SellerMapper sellerMapper;

    public SellerServiceImpl(SellerRepository sellerRepository, CarRepository carRepository) {
        this.sellerRepository = sellerRepository;
        this.carRepository = carRepository;
    }

    @Override
    public SellerResponseDTO getSellerById(Long id) {
        Optional<Seller> optionalCar = sellerRepository.findById(id);
        if (optionalCar.isPresent()) {
            return sellerMapper.toDto(optionalCar.get());
        } else {
            throw new CarNotFoundException(id);
        }
    }

    @Override
    public Seller addSeller(Seller seller) {
        sellerRepository.save(seller);
        return seller;
    }

    @Override
    public Car addCarToSeller(Long sellerId, Car car) {
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new SellerNotFoundException(sellerId));
        car.setSeller(seller);
        return carRepository.save(car);
    }

    @Override
    public void removeCarFromSeller(Long sellerId, Long carId) {
        Optional<Car> optionalCar = carRepository.findById(carId);
        if (optionalCar.isPresent()) {
            Car car = optionalCar.get();
            if (car.getSeller() != null && car.getSeller().getId().equals(sellerId)) {
                carRepository.delete(car);
            } else {
                throw new IllegalArgumentException("Car does not belong to the specified seller");
            }
        } else {
            throw new CarNotFoundException(carId);
        }
    }


    @Override
    @Transactional(readOnly = true)
    public Page<Car> searchCars(String mark, String model, String city, Double minPrice, Double maxPrice, String type, Integer minYear, Integer maxYear, Integer minMileage, Integer maxMileage, Integer minSeats, Integer minHorsePower, Pageable pageable) {
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


        if (pageable.getSort().isSorted()) {
            List<Order> orders = new ArrayList<>();
            for (Sort.Order sortOrder : pageable.getSort()) {
                if (sortOrder.getProperty().equals("price")) {
                    Path<Double> pricePath = root.get("price");
                    Order order = sortOrder.getDirection().isAscending() ?
                            criteriaBuilder.asc(pricePath) : criteriaBuilder.desc(pricePath);
                    orders.add(order);
                }
            }
            query.orderBy(orders);
        }

        // Order by the specified field and direction
        TypedQuery<Car> typedQuery = entityManager.createQuery(query);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        // Return the result as a Page
        List<Car> cars = typedQuery.getResultList();
        long total = entityManager.createQuery(query).getResultStream().count();
        return new PageImpl<>(cars, pageable, total);
    }

}


