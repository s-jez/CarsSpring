package com.example.cars.repository;

import com.example.cars.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllCarsByMake(String make);
}
