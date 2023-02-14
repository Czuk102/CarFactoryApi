package com.portfolio.carfactoryapi.repository;

import com.portfolio.carfactoryapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarFactoryRepository extends JpaRepository<Car,Long> {
}
