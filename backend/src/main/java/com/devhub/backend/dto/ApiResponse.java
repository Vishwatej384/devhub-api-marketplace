package com.devhub.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private Long id;
    private String name;
    private String description;
    private String endpoint;
    private String method;
    private CreatedByInfo createdBy;
    private LocalDateTime createdAt;
    private Double averageRating;
    private Long ratingCount;
    private Integer userRating;
    private boolean isSubscribed;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreatedByInfo {
        private Long id;
        private String name;
        private String email;
    }
}
