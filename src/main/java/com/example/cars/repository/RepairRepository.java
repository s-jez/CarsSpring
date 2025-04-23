package com.example.cars.repository;

import com.example.cars.entity.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long> {
    List<Repair> findAllRepairsByCarId(Long carId);
}
