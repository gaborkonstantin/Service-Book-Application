package com.servicebook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDateTime;

public class CarDto {

    @Data
    public static class CreateCarRequest {
        @NotBlank private String brand;
        @NotBlank private String model;
        @NotNull private Integer year;
        private String licensePlate;
        private String vin;
        private Integer currentMileage;
    }

    @Data
    public static class CarResponse {
        private Long id;
        private String brand;
        private String model;
        private Integer year;
        private String licensePlate;
        private String vin;
        private Integer currentMileage;
        private LocalDateTime createdAt;
        private int serviceRecordCount;
    }
}
