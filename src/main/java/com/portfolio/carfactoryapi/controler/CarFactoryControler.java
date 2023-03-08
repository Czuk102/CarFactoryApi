package com.portfolio.carfactoryapi.controler;

import com.portfolio.carfactoryapi.model.Car;
import com.portfolio.carfactoryapi.model.Equipment;
import com.portfolio.carfactoryapi.service.CarFactoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
public class CarFactoryControler {
    public final CarFactoryService carFactoryService;

    public CarFactoryControler(CarFactoryService carFactoryService) {
        this.carFactoryService = carFactoryService;
    }

    @GetMapping("/hello")
    public ResponseEntity<String> helloWorld() {
        return ResponseEntity.ok("Hello world");
    }

    @Operation(description = "Makes a car default car ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Polonez successfully made and saved"),
            @ApiResponse(responseCode = "401", description = "JWT not valid")})
    @PostMapping("/cars/makePolonez")
    public ResponseEntity<Car> makeCar() {
        return ResponseEntity.ok(carFactoryService.makePolonez());
    }

    @Operation(description = "Makes car ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car successfully made and saved "),
            @ApiResponse(responseCode = "401", description = "JWT not valid")})
    @PostMapping("/cars")
    public ResponseEntity<Car> makeCar(@Parameter(description = "Car to make and save", required = true)
                                       @RequestBody Car car) {
        return ResponseEntity.ok(carFactoryService.makeCar(car));
    }

    @Operation(description = "Updates a car pointed by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car found"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "401", description = "JWT not valid")})
    @PatchMapping("/cars/{id}")
    public ResponseEntity<Car> updateCar(@Parameter(description = "Cars id to update and save", required = true)
                                         @PathVariable Long id,
                                         @Parameter(description = "Car updated details to save", required = true)
                                         @RequestBody Car carDetails) {
        return ResponseEntity.ok(carFactoryService.updateCar(id, carDetails));
    }

    @Operation(description = "Finds a car by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car found"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "401", description = "JWT not valid")})
    @GetMapping("/cars/{id}")
    public ResponseEntity<Car> getById(@Parameter(description = "Id of car to be found")
                                       @PathVariable Long id) {
        return ResponseEntity.ok(carFactoryService.findById(id));
    }

    @Operation(description = "Deletes a car by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Car successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "401", description = "JWT not valid")})
    @DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteByid(@Parameter(description = "Id of car to be deleted")
                                           @PathVariable Long id) {
        return ResponseEntity.ok(carFactoryService.deleteById(id));
    }

    @Operation(description = "View all cars")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars successfully retrieved"),
            @ApiResponse(responseCode = "401", description = "JWT not valid")})
    @GetMapping("/cars/all")
    public ResponseEntity<List<Car>> findAll() {
        return ResponseEntity.ok(carFactoryService.findAll());
    }

    @GetMapping("/cars/topfive")
    public ResponseEntity<List<Car>> getTopFiveCars() {
        return ResponseEntity.ok(carFactoryService.getTopFiveCars());
    }

    @Operation(description = "Views final price (base + equipment) of a car by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cars price successfully retrieved"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "401", description = "JWT not valid")})
    @GetMapping("/cars/finalPrice/{id}")
    public ResponseEntity<Double> getFinalPricebyId(@PathVariable Long id) {
        return ResponseEntity.ok(carFactoryService.getFinalPrice(carFactoryService.findById(id)));
    }

    @Operation(description = "Adds equipment to a car indicated by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Equipment successfully added"),
            @ApiResponse(responseCode = "404", description = "Car not found"),
            @ApiResponse(responseCode = "401", description = "JWT not valid")})
    @PatchMapping("/cars/{id}/equipments/add")
    public ResponseEntity<Car> addEquipment(@PathVariable Long id, @RequestBody Equipment equipment) {
        return ResponseEntity.ok(carFactoryService.addEquipmentById(id, equipment));
    }
}
