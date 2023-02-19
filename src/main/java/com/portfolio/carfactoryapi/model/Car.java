package com.portfolio.carfactoryapi.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double engineCapacity;
    private Engine engine;

    @OneToMany()
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Equipment> equipments;
    private Double price;

    public Car() {
    }

    public Car(Long id, String name, double engineCapacity, Engine engine, List<Equipment> equipments, Double price) {
        this.id = id;
        this.name = name;
        this.engineCapacity = engineCapacity;
        this.engine = engine;
        this.equipments = equipments;
        this.price = price;
    }

    public Car(String name, Double engineCapacity, Engine engine, List<Equipment> equipments, Double price) {
        this.name = name;
        this.engineCapacity = engineCapacity;
        this.engine = engine;
        this.equipments = equipments;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public List<Equipment> getEquipments() {
        return equipments;
    }

    public void setEquipments(List<Equipment> equipments) {
        this.equipments = equipments;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", engineCapacity=" + engineCapacity +
                ", engine=" + engine +
                ", equipments=" + equipments +
                ", price=" + price +
                '}';
    }
}
