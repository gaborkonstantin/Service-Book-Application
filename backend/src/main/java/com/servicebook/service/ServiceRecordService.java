package com.servicebook.service;

import com.servicebook.dto.ServiceImageDto;
import com.servicebook.dto.ServiceRecordDto;
import com.servicebook.model.Car;
import com.servicebook.model.ServiceRecord;
import com.servicebook.repository.CarRepository;
import com.servicebook.repository.ServiceRecordRepository;
import com.servicebook.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceRecordService {

    private final ServiceRecordRepository serviceRecordRepository;
    private final CarRepository carRepository;

    private Long getCurrentUserId() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        return principal.getId();
    }

    public List<ServiceRecordDto.ServiceRecordResponse> getServiceRecords(Long carId) {
        carRepository.findByIdAndUserId(carId, getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Car not found: " + carId));
        return serviceRecordRepository.findByCarIdOrderByServiceDateDesc(carId)
                .stream().map(this::toResponse).collect(Collectors.toList());
    }

    public ServiceRecordDto.ServiceRecordResponse createServiceRecord(
            Long carId, ServiceRecordDto.CreateServiceRecordRequest request) {
        Car car = carRepository.findByIdAndUserId(carId, getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Car not found: " + carId));

        ServiceRecord record = ServiceRecord.builder()
                .car(car)
                .serviceDate(request.getServiceDate())
                .serviceType(request.getServiceType())
                .description(request.getDescription())
                .mileage(request.getMileage())
                .cost(request.getCost())
                .serviceProvider(request.getServiceProvider())
                .build();

        return toResponse(serviceRecordRepository.save(record));
    }

    public void deleteServiceRecord(Long carId, Long recordId) {
        carRepository.findByIdAndUserId(carId, getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Car not found: " + carId));
        ServiceRecord record = serviceRecordRepository.findByIdAndCarId(recordId, carId)
                .orElseThrow(() -> new RuntimeException("Record not found: " + recordId));
        serviceRecordRepository.delete(record);
    }

    private ServiceRecordDto.ServiceRecordResponse toResponse(ServiceRecord r) {
        ServiceRecordDto.ServiceRecordResponse resp = new ServiceRecordDto.ServiceRecordResponse();
        resp.setId(r.getId());
        resp.setServiceDate(r.getServiceDate());
        resp.setServiceType(r.getServiceType());
        resp.setDescription(r.getDescription());
        resp.setMileage(r.getMileage());
        resp.setCost(r.getCost());
        resp.setServiceProvider(r.getServiceProvider());
        resp.setCreatedAt(r.getCreatedAt());
        resp.setImages(r.getImages().stream().map(img -> {
            ServiceImageDto.ImageResponse ir = new ServiceImageDto.ImageResponse();
            ir.setId(img.getId());
            ir.setImageUrl(img.getImageUrl());
            ir.setFileName(img.getFileName());
            ir.setUploadedAt(img.getUploadedAt());
            return ir;
        }).collect(Collectors.toList()));
        return resp;
    }
}
