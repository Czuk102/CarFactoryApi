package com.portfolio.carfactoryapi.service;

import com.portfolio.carfactoryapi.exception.CarNotFoudException;
import com.portfolio.carfactoryapi.model.Car;
import com.portfolio.carfactoryapi.model.Engine;
import com.portfolio.carfactoryapi.model.Equipment;
import com.portfolio.carfactoryapi.repository.CarFactoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CarFactoryService {
    private final CarFactoryRepository carFactoryRepository;

    public CarFactoryService(CarFactoryRepository carFactoryRepository) {
        this.carFactoryRepository = carFactoryRepository;
    }

    public Car makePolonez() {
        Equipment ac = new Equipment(null, "ac", 1000.);
        Equipment radio = new Equipment(null, "radio", 800.);
        List<Equipment> equipments = new ArrayList<>(List.of(ac, radio));
        Car car = new Car(null, "Polonez", 1.6, Engine.PETROL, equipments, 50000.);
        carFactoryRepository.save(car);
        return car;
    }

    public Car makeCar(Car car) {
        return carFactoryRepository.save(car);
    }

    public Car updateCar(Long id, Car carDetails) {
        Car car = findById(id);
        car.setName(carDetails.getName());
        car.setEngine(carDetails.getEngine());
        car.setEquipments(carDetails.getEquipments());
        car.setEngineCapacity(carDetails.getEngineCapacity());
        car.setPrice(carDetails.getPrice());
        carFactoryRepository.save(car);
        return car;
    }

    public Car findById(Long id) {
        return carFactoryRepository.findById(id).orElseThrow(() -> new CarNotFoudException(id));
    }

    public Car addEquipmentById(Long id, Equipment equipment) {
        Car car = findById(id);
        if (car.getEquipments() != null) {
            car.getEquipments().add(equipment);
        } else {
            car.setEquipments(Collections.singletonList(equipment));
        }
        return carFactoryRepository.save(car);
    }

    public void setName(Car car, String name) {
        if (car.getName() != null) {
            car.setName(name);
        }
    }

    public Double getEquipmentPrice(Car car) {
        List<Equipment> equipments = car.getEquipments();
        if (equipments != null) {
            Double price = 0d;
            if (equipments.size() == 0) {
                return price;
            } else {
                for (Equipment equipment : equipments) {
                    price += equipment.getPrice();
                }
            }
            return price;
        } else {
            throw new RuntimeException("Equipment are null");
        }
    }

    public Double getFinalPrice(Car car) {
        Double basePrice = car.getPrice();
        if (basePrice != null && getEquipmentPrice(car) != null) {
            return basePrice + getEquipmentPrice(car);
        } else {
            throw new RuntimeException("Base price is null");
        }
    }

    public List<Car> getTopFiveCars() {
        List<Long> ids = List.of(1L, 2L, 3L, 4L, 5L);
        return carFactoryRepository.findAllById(ids);
    }

    public List<Car> findAll() {
        return carFactoryRepository.findAll();
    }

    public void delete(Car car) {
        carFactoryRepository.delete(car);
    }

    public Void deleteById(Long id) {
        if (!carFactoryRepository.existsById(id)) {
            throw new CarNotFoudException(id);
        }
        carFactoryRepository.deleteById(id);
        return null;
    }

    public boolean exists(Long id) {
        return carFactoryRepository.existsById(id);
    }

}
