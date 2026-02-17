package com.servicebook.repository;

import com.servicebook.model.ServiceImage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ServiceImageRepository extends JpaRepository<ServiceImage, Long> {
    List<ServiceImage> findByServiceRecordId(Long serviceRecordId);
}
