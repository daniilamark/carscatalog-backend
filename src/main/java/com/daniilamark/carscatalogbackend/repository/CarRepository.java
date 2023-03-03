package com.daniilamark.carscatalogbackend.repository;

import com.daniilamark.carscatalogbackend.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
