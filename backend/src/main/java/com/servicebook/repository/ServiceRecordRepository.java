package com.servicebook.repository;

import com.servicebook.model.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    List<ServiceRecord> findByCarIdOrderByServiceDateDesc(Long carId);
    Optional<ServiceRecord> findByIdAndCarId(Long id, Long carId);
}
