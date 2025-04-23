package com.example.cars.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "repairs")
public class Repair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private LocalDate date;
    private Double cost;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    public Repair() {}

    public Repair(String description, LocalDate date, Double cost) {
        this.description = description;
        this.date = date;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
