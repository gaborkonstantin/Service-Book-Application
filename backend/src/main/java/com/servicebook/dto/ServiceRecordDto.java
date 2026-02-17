package com.servicebook.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class ServiceRecordDto {

    @Data
    public static class CreateServiceRecordRequest {
        @NotNull private LocalDate serviceDate;
        @NotBlank private String serviceType;
        private String description;
        private Integer mileage;
        private BigDecimal cost;
        private String serviceProvider;
    }

    @Data
    public static class ServiceRecordResponse {
        private Long id;
        private LocalDate serviceDate;
        private String serviceType;
        private String description;
        private Integer mileage;
        private BigDecimal cost;
        private String serviceProvider;
        private LocalDateTime createdAt;
        private List<ServiceImageDto.ImageResponse> images;
    }
}
