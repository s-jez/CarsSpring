package com.example.cars.service;

import com.example.cars.entity.Car;
import com.example.cars.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> findAllCars() {
        return carRepository.findAll();
    }

    public Optional<Car> findCarById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> findCarsByMake(String make) {
        return carRepository.findAllCarsByMake(make);
    }

    public Car saveCar(Car car) {
        return carRepository.save(car);
    }

    public Car updateCar(Long id, Car carDetails) {
        Car car = carRepository.findById(id).orElseThrow();
        car.setMake(carDetails.getMake());
        car.setModel(carDetails.getModel());
        car.setYear(carDetails.getYear());
        car.setVin(carDetails.getVin());
        return carRepository.save(car);
    }

    public void deleteCarById(Long id) {
        carRepository.deleteById(id);
    }
}
