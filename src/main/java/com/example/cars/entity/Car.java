package com.example.cars.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String make;
    private String model;
    private Integer year;
    private String vin;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore  // Prevents serialization
    private List<Repair> repairs = new ArrayList<>();

    public Car() {}

    public Car(String make, String model, Integer year, String vin) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.vin = vin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public List<Repair> getRepairs() {
        return repairs;
    }

    public void setRepairs(List<Repair> repairs) {
        this.repairs = repairs;
    }

    public void addRepair(Repair repair) {
        repairs.add(repair);
        if(repair.getCar() != this) {
            repair.setCar(this);
        }
    }

    public void removeRepair(Repair repair) {
        repairs.remove(repair);
        repair.setCar(null);
    }
}
