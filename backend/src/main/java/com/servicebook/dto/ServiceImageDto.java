package com.servicebook.dto;

import lombok.Data;
import java.time.LocalDateTime;

public class ServiceImageDto {

    @Data
    public static class ImageResponse {
        private Long id;
        private String imageUrl;
        private String fileName;
        private LocalDateTime uploadedAt;
    }
}
