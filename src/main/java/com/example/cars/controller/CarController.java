package com.example.cars.controller;

import com.example.cars.entity.Car;
import com.example.cars.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAllCars(@RequestParam(name = "make", required = false) String make) {
        List<Car> cars;
        if(make != null && !make.isEmpty()) {
            cars = carService.findCarsByMake(make);
        } else {
            cars = carService.findAllCars();
        }
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCarById(@PathVariable Long id) {
        Optional<Car> foundCar = carService.findCarById(id);
        if (foundCar.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundCar.get());
        }
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody Car car) {
        if(car == null || car.getMake() == null || car.getModel() == null || car.getYear() == null || car.getVin() == null) {
            return ResponseEntity.badRequest().body(null);
        } else {
            Car createdCar = carService.saveCar(car);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCar);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car car) {
        try {
            Car updatedCar = carService.updateCar(id, car);
            return ResponseEntity.ok(updatedCar);
        }
        catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long id) {
        Optional<Car> foundCar = carService.findCarById(id);
        if (foundCar.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        carService.deleteCarById(id);
        return ResponseEntity.noContent().build();
    }
}
