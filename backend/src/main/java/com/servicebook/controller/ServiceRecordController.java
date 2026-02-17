package com.servicebook.controller;

import com.servicebook.dto.ServiceRecordDto;
import com.servicebook.service.ServiceRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars/{carId}/records")
@RequiredArgsConstructor
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;

    @GetMapping
    public ResponseEntity<List<ServiceRecordDto.ServiceRecordResponse>> getServiceRecords(
            @PathVariable Long carId) {
        return ResponseEntity.ok(serviceRecordService.getServiceRecords(carId));
    }

    @PostMapping
    public ResponseEntity<ServiceRecordDto.ServiceRecordResponse> createServiceRecord(
            @PathVariable Long carId,
            @Valid @RequestBody ServiceRecordDto.CreateServiceRecordRequest request) {
        return ResponseEntity.ok(serviceRecordService.createServiceRecord(carId, request));
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<Void> deleteServiceRecord(
            @PathVariable Long carId,
            @PathVariable Long recordId) {
        serviceRecordService.deleteServiceRecord(carId, recordId);
        return ResponseEntity.noContent().build();
    }
}
