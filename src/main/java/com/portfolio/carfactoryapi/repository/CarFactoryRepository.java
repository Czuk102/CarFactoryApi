package com.portfolio.carfactoryapi.repository;

import com.portfolio.carfactoryapi.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarFactoryRepository extends JpaRepository<Car,Long> {
}
