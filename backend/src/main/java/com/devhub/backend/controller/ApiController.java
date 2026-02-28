package com.devhub.backend.controller;

import com.devhub.backend.dto.ApiResponse;
import com.devhub.backend.dto.RatingRequest;
import com.devhub.backend.entity.Api;
import com.devhub.backend.entity.Rating;
import com.devhub.backend.entity.User;
import com.devhub.backend.service.ApiService;
import com.devhub.backend.service.RatingService;
import com.devhub.backend.service.SubscriptionService;
import com.devhub.backend.service.UserService;
import com.devhub.backend.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class ApiController {

    private final ApiService apiService;
    private final UserService userService;
    private final RatingService ratingService;
    private final SubscriptionService subscriptionService;
    private final JwtUtil jwtUtil;

    public ApiController(ApiService apiService, 
                        UserService userService, 
                        RatingService ratingService,
                        SubscriptionService subscriptionService,
                        JwtUtil jwtUtil) {
        this.apiService = apiService;
        this.userService = userService;
        this.ratingService = ratingService;
        this.subscriptionService = subscriptionService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/create")
    public Api createApi(@RequestBody Api api, @RequestHeader("Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userService.getUserByEmail(email);
        api.setCreatedBy(user);
        return apiService.createApi(api);
    }

    @GetMapping("/all")
    public List<ApiResponse> getAllApis(@RequestHeader(value = "Authorization", required = false) String token) {
        List<Api> apis = apiService.getAllApis();
        String userEmail = null;
        
        if (token != null && !token.isEmpty()) {
            userEmail = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        }
        
        final String email = userEmail;
        return apis.stream().map(api -> convertToApiResponse(api, email)).collect(Collectors.toList());
    }

    @GetMapping("/my-apis")
    public List<Api> getMyApis(@RequestHeader("Authorization") String token) {
        String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
        User user = userService.getUserByEmail(email);
        return apiService.getApisByUser(user);
    }

    @GetMapping("/{id}")
    public Api getApiById(@PathVariable Long id) {
        return apiService.getApiById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteApi(@PathVariable Long id) {
        apiService.deleteApi(id);
    }

    @PostMapping("/rate")
    public ResponseEntity<?> rateApi(@RequestBody RatingRequest request, 
                                     @RequestHeader("Authorization") String token) {
        try {
            String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
            Rating rating = ratingService.rateApi(email, request.getApiId(), 
                                                  request.getRating(), request.getComment());
            return ResponseEntity.ok(rating);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    private ApiResponse convertToApiResponse(Api api, String userEmail) {
        ApiResponse response = new ApiResponse();
        response.setId(api.getId());
        response.setName(api.getName());
        response.setDescription(api.getDescription());
        response.setEndpoint(api.getEndpoint());
        response.setMethod(api.getMethod());
        response.setCreatedAt(api.getCreatedAt());
        
        if (api.getCreatedBy() != null) {
            ApiResponse.CreatedByInfo createdBy = new ApiResponse.CreatedByInfo();
            createdBy.setId(api.getCreatedBy().getId());
            createdBy.setName(api.getCreatedBy().getName());
            createdBy.setEmail(api.getCreatedBy().getEmail());
            response.setCreatedBy(createdBy);
        }
        
        // Add rating info
        response.setAverageRating(ratingService.getAverageRating(api.getId()));
        response.setRatingCount(ratingService.getRatingCount(api.getId()));
        
        if (userEmail != null) {
            response.setUserRating(ratingService.getUserRating(userEmail, api.getId()));
            response.setSubscribed(subscriptionService.isUserSubscribed(userEmail, api.getId()));
        }
        
        return response;
    }

    static class ErrorResponse {
        private String message;

        public ErrorResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
