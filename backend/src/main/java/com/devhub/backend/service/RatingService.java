package com.devhub.backend.service;

import com.devhub.backend.entity.Api;
import com.devhub.backend.entity.Rating;
import com.devhub.backend.entity.User;
import com.devhub.backend.repository.ApiRepository;
import com.devhub.backend.repository.RatingRepository;
import com.devhub.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingService {

    private final RatingRepository ratingRepository;
    private final ApiRepository apiRepository;
    private final UserRepository userRepository;

    public RatingService(RatingRepository ratingRepository,
                        ApiRepository apiRepository,
                        UserRepository userRepository) {
        this.ratingRepository = ratingRepository;
        this.apiRepository = apiRepository;
        this.userRepository = userRepository;
    }

    public Rating rateApi(String userEmail, Long apiId, Integer rating, String comment) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Api api = apiRepository.findById(apiId)
                .orElseThrow(() -> new RuntimeException("API not found"));

        if (rating < 1 || rating > 5) {
            throw new RuntimeException("Rating must be between 1 and 5");
        }

        // Check if user already rated this API
        Optional<Rating> existingRating = ratingRepository.findByUserIdAndApiId(user.getId(), apiId);
        
        Rating ratingEntity;
        if (existingRating.isPresent()) {
            // Update existing rating
            ratingEntity = existingRating.get();
            ratingEntity.setRating(rating);
            ratingEntity.setComment(comment);
        } else {
            // Create new rating
            ratingEntity = new Rating();
            ratingEntity.setUser(user);
            ratingEntity.setApi(api);
            ratingEntity.setRating(rating);
            ratingEntity.setComment(comment);
        }

        return ratingRepository.save(ratingEntity);
    }

    public Double getAverageRating(Long apiId) {
        Double avg = ratingRepository.getAverageRatingForApi(apiId);
        return avg != null ? avg : 0.0;
    }

    public Long getRatingCount(Long apiId) {
        return ratingRepository.getCountForApi(apiId);
    }

    public Integer getUserRating(String userEmail, Long apiId) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return null;
        }

        Optional<Rating> rating = ratingRepository.findByUserIdAndApiId(user.getId(), apiId);
        return rating.map(Rating::getRating).orElse(null);
    }
}
