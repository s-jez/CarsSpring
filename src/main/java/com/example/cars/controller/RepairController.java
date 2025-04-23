package com.example.cars.controller;

import com.example.cars.entity.Car;
import com.example.cars.entity.Repair;
import com.example.cars.service.RepairService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/repairs")
public class RepairController {

    private final RepairService repairService;

    public RepairController(RepairService repairService) {
        this.repairService = repairService;
    }

    @GetMapping
    public ResponseEntity<List<Repair>> getAllRepairs(@RequestParam(name = "carId", required = false) Long carId) {
        List<Repair> repairs;
        if(carId != null) {
            repairs = repairService.findRepairsByCarId(carId);
        } else {
            repairs = repairService.findAllRepairs();
        }
        return ResponseEntity.ok(repairs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Repair> getRepairById(@PathVariable Long id) {
        Optional<Repair> foundRepair = repairService.getRepairById(id);
        if (foundRepair.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundRepair.get());
        }
    }

    @PostMapping
    public ResponseEntity<Repair> createRepair(@RequestBody Repair repair, @RequestParam Long carId) {
        Repair createdRepair = repairService.saveRepair(repair, carId);
        if (createdRepair != null) {
            return ResponseEntity.ok(createdRepair);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Repair> updateRepair(@PathVariable Long id, @RequestBody Repair repair) {
        Repair updatedRepair = repairService.updateRepair(id, repair);
        if (updatedRepair != null) {
            return ResponseEntity.ok(updatedRepair);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Repair> deleteRepair(@PathVariable Long id) {
        if(repairService.getRepairById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repairService.deleteRepair(id);
        return ResponseEntity.noContent().build();
    }
}