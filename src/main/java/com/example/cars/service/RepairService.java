package com.example.cars.service;

import com.example.cars.entity.Car;
import com.example.cars.entity.Repair;
import com.example.cars.repository.CarRepository;
import com.example.cars.repository.RepairRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepairService {
    private final RepairRepository repairRepository;
    private final CarRepository carRepository;

    public RepairService(RepairRepository repairRepository, CarRepository carRepository) {
        this.repairRepository = repairRepository;
        this.carRepository = carRepository;
    }

    public List<Repair> findAllRepairs() {
        return repairRepository.findAll();
    }

    public List<Repair> findRepairsByCarId(Long carId) {
        return repairRepository.findAllRepairsByCarId(carId);
    }

    public Optional<Repair> getRepairById(Long id) {
        return repairRepository.findById(id);
    }

    public Repair saveRepair(Repair repair, Long carId) {
        Car car = carRepository.findById(carId).orElseThrow();
        car.addRepair(repair);
        return repairRepository.save(repair);
    }

    public Repair updateRepair(Long repairId, Repair repairDetails) {
        Repair repair = repairRepository.findById(repairId).orElseThrow();
        repair.setCar(repairDetails.getCar());
        repair.setDescription(repairDetails.getDescription());
        repair.setDate(repairDetails.getDate());
        return repairRepository.save(repair);
    }

    public void deleteRepair(Long repairId) {
        Repair repair = repairRepository.findById(repairId).orElseThrow();
        Car car = repair.getCar();
        car.removeRepair(repair);
        repairRepository.deleteById(repairId);
    }
}
