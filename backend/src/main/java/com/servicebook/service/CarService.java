package com.servicebook.service;

import com.servicebook.dto.CarDto;
import com.servicebook.model.Car;
import com.servicebook.model.User;
import com.servicebook.repository.CarRepository;
import com.servicebook.repository.UserRepository;
import com.servicebook.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;
    private final UserRepository userRepository;

    private Long getCurrentUserId() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return principal.getId();
    }

    public List<CarDto.CarResponse> getUserCars() {
        return carRepository.findByUserId(getCurrentUserId())
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public CarDto.CarResponse getCarById(Long carId) {
        Car car = carRepository.findByIdAndUserId(carId, getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Car not found: " + carId));
        return toResponse(car);
    }

    public CarDto.CarResponse createCar(CarDto.CreateCarRequest request) {
        User user = userRepository.findById(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Car car = Car.builder()
                .user(user)
                .brand(request.getBrand())
                .model(request.getModel())
                .year(request.getYear())
                .licensePlate(request.getLicensePlate())
                .vin(request.getVin())
                .currentMileage(request.getCurrentMileage())
                .build();

        return toResponse(carRepository.save(car));
    }

    public CarDto.CarResponse updateCar(Long carId, CarDto.CreateCarRequest request) {
        Car car = carRepository.findByIdAndUserId(carId, getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Car not found: " + carId));

        car.setBrand(request.getBrand());
        car.setModel(request.getModel());
        car.setYear(request.getYear());
        car.setLicensePlate(request.getLicensePlate());
        car.setVin(request.getVin());
        car.setCurrentMileage(request.getCurrentMileage());

        return toResponse(carRepository.save(car));
    }

    public void deleteCar(Long carId) {
        Car car = carRepository.findByIdAndUserId(carId, getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Car not found: " + carId));
        carRepository.delete(car);
    }

    private CarDto.CarResponse toResponse(Car car) {
        CarDto.CarResponse r = new CarDto.CarResponse();
        r.setId(car.getId());
        r.setBrand(car.getBrand());
        r.setModel(car.getModel());
        r.setYear(car.getYear());
        r.setLicensePlate(car.getLicensePlate());
        r.setVin(car.getVin());
        r.setCurrentMileage(car.getCurrentMileage());
        r.setCreatedAt(car.getCreatedAt());
        r.setServiceRecordCount(car.getServiceRecords().size());
        return r;
    }
}
