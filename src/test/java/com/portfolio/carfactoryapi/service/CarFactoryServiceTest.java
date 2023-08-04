package com.portfolio.carfactoryapi.service;

import com.portfolio.carfactoryapi.exception.CarNotFoudException;
import com.portfolio.carfactoryapi.model.Car;
import com.portfolio.carfactoryapi.model.Engine;
import com.portfolio.carfactoryapi.model.Equipment;
import com.portfolio.carfactoryapi.repository.CarFactoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CarFactoryServiceTest {
    @Mock
    private CarFactoryRepository carFactoryRepository;
    @InjectMocks
    private CarFactoryService carFactoryService;

    private Car carToSave;

    @BeforeEach
    void setUp() {
        final var heatedSeats = new Equipment(null, "heated front seats", 6000d);
        final var radio = new Equipment(null, "radio", 3000d);
        final var equipments = new ArrayList<>(Arrays.asList(heatedSeats, radio));
        carToSave = new Car("A8", 2.0, Engine.PETROL, equipments, 100_000d, 2000);
    }

    @Test
    void shouldFindCarByIdAndReturn() {
        // arrange
        when(carFactoryRepository.findById(anyLong())).thenReturn(Optional.ofNullable(carToSave));

        // act
        final var actual = carFactoryService.findById(1L);

        // assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(carToSave);
        verify(carFactoryRepository, times(1)).findById(any());
        verifyNoMoreInteractions(carFactoryRepository);
    }

    @Test
    void shouldNotFindCarByIdAndThrowsException() {
        // arrange
        when(carFactoryRepository.findById(anyLong())).thenReturn(Optional.empty());

        //act
        Assertions.assertThrows(CarNotFoudException.class, () -> carFactoryService.findById(1L));
        verify(carFactoryRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(carFactoryRepository);

    }

    @Test
    void shouldSaveAndReturnPolonez() {
        // arrange
        Equipment ac = new Equipment(null, "ac", 1000.);
        Equipment radio = new Equipment(null, "radio", 800.);
        List<Equipment> equipments = new ArrayList<>(List.of(ac, radio));
        Car polonezToSave = new Car(null, "Polonez", 1.6, Engine.PETROL, equipments, 50000., 2000);
        when(carFactoryRepository.save(any(Car.class))).thenReturn(polonezToSave);

        // act
        final var actual = carFactoryService.makePolonez();

        // assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(polonezToSave);
        assertThat(actual).isNotNull();
        verify(carFactoryRepository, times(1)).save(any(Car.class));
        verifyNoMoreInteractions(carFactoryRepository);
    }

    @Test
    void shouldSaveGivenCarAndReturn() {
        // arrange
        when(carFactoryRepository.save(any(Car.class))).thenReturn(carToSave);

        // act
        final var actual = carFactoryService.makeCar(carToSave);

        // assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(carToSave);
        verify(carFactoryRepository, times(1)).save(any(Car.class));
        verifyNoMoreInteractions(carFactoryRepository);
    }

    @Test
    void shoudUpdateExistingCarAndReturnNew() {
        // arrange
        when(carFactoryRepository.findById(any())).thenReturn(Optional.ofNullable(carToSave));
        Car updatedCar = new Car("mx-8", 3.0, Engine.PETROL, null, 300_000d, 2000);

        // act
        Car returnedCar = carFactoryService.updateCar(1L, updatedCar);

        // assert
        assertThat(updatedCar).isNotEqualTo(carToSave);
        assertThat(returnedCar).usingRecursiveComparison().isEqualTo(updatedCar);
        verify(carFactoryRepository, times(1)).save(any(Car.class));
        verifyNoMoreInteractions(carFactoryRepository);
    }

    @Test
    void shouldAddEquipmentToGivenCar() {
        // arrange
        Equipment headsUpDisplay = new Equipment(null, "heads up display", 12_000d);
        when(carFactoryRepository.findById(anyLong())).thenReturn(Optional.of(carToSave));

        // act
        carFactoryService.addEquipmentById(1L, headsUpDisplay);

        // assert
        assertThat(carToSave.getEquipments()).containsOnlyOnce(headsUpDisplay);
        verify(carFactoryRepository, times(1)).findById(anyLong());
        verify(carFactoryRepository, times(1)).save(any(Car.class));
    }

    @Test
    void shouldGetEguipmentPriceOfGivenCar() {
        // arrange
        final var expectedPrice = 9_000d;

        // act
        final var actualPrice = carFactoryService.getEquipmentPrice(carToSave);

        // assert
        assertThat(actualPrice).isEqualTo(expectedPrice);
    }

    @Test
    void shouldGetCarPriceWithEquipment() {
        // arrange
        final var expected = 109_000d;
        //act
        final var actual = carFactoryService.getFinalPrice(carToSave);

        // assert
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void shouldGetTopFiveCars() {
        // arrange
        List<Car> topFive = new ArrayList<>(List.of(new Car(), new Car(), new Car(), new Car(), new Car()));
        when(carFactoryRepository.findAllById(any())).thenReturn(topFive);
        //act
        final var actual = carFactoryService.getTopFiveCars();
        // assert
        assertThat(actual).hasSize(5);
        assertThat(actual).isEqualTo(topFive);
    }

    @Test
    void shouldCheckIfCarExistsAndReturnBoolean() {
        when(carFactoryRepository.existsById(any())).thenReturn(true);
        boolean actual = carFactoryService.exists(1L);
        assertThat(actual).isTrue();
    }
}