package com.servicebook.controller;

import com.servicebook.dto.CarDto;
import com.servicebook.service.CarService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping
    public ResponseEntity<List<CarDto.CarResponse>> getUserCars() {
        return ResponseEntity.ok(carService.getUserCars());
    }

    @GetMapping("/{carId}")
    public ResponseEntity<CarDto.CarResponse> getCarById(@PathVariable Long carId) {
        return ResponseEntity.ok(carService.getCarById(carId));
    }

    @PostMapping
    public ResponseEntity<CarDto.CarResponse> createCar(@Valid @RequestBody CarDto.CreateCarRequest request) {
        return ResponseEntity.ok(carService.createCar(request));
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarDto.CarResponse> updateCar(
            @PathVariable Long carId,
            @Valid @RequestBody CarDto.CreateCarRequest request) {
        return ResponseEntity.ok(carService.updateCar(carId, request));
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable Long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.noContent().build();
    }
}
