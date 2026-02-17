package com.servicebook.repository;

import com.servicebook.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByUserId(Long userId);
    Optional<Car> findByIdAndUserId(Long id, Long userId);
}
