package com.portfolio.carfactoryapi.controler;

import com.portfolio.carfactoryapi.model.Car;
import com.portfolio.carfactoryapi.service.CarFactoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarFactoryControler {
    public final CarFactoryService carFactoryService;

    public CarFactoryControler(CarFactoryService carFactoryService) {
        this.carFactoryService = carFactoryService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello world");
    }

    @PostMapping("/cars/makePolonez")
    public ResponseEntity<Car> makeCar() {
        return ResponseEntity.ok(carFactoryService.makePolonez());
    }

    @PostMapping("/cars")
    public ResponseEntity<Car> makeCar(@RequestBody Car car) {
        return ResponseEntity.ok(carFactoryService.makeCar(car));
    }

    @PatchMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@PathVariable Long id, @RequestBody Car carDetails) {
        return ResponseEntity.ok(carFactoryService.updateCar(id, carDetails));
    }

    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getById(@PathVariable Long id) {
        return ResponseEntity.ok(carFactoryService.findById(id));
    }

    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteByid(@PathVariable Long id) {
        return ResponseEntity.ok(carFactoryService.deleteById(id));
    }

    @GetMapping("/cars/all")
    public ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.ok(carFactoryService.findAll());
    }

    @GetMapping("/cars/topfive")
    public ResponseEntity<List<Car>> getTopFiveCars() {
        return ResponseEntity.ok(carFactoryService.getTopFiveCars());
    }

    @GetMapping("/cars/finalPrice/{id}")
    public ResponseEntity<Double> getFinalPricebyId(@PathVariable Long id) {
        return ResponseEntity.ok(carFactoryService.getFinalPrice(carFactoryService.findById(id)));
    }


}
