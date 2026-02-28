package com.devhub.backend.repository;

import com.devhub.backend.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
    
    List<Rating> findByApiId(Long apiId);
    
    Optional<Rating> findByUserIdAndApiId(Long userId, Long apiId);
    
    @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.api.id = :apiId")
    Double getAverageRatingForApi(Long apiId);
    
    @Query("SELECT COUNT(r) FROM Rating r WHERE r.api.id = :apiId")
    Long getCountForApi(Long apiId);
}
