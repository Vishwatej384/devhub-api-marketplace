package com.devhub.backend.controller;

import com.devhub.backend.entity.Api;
import com.devhub.backend.entity.Subscription;
import com.devhub.backend.entity.User;
import com.devhub.backend.service.ApiService;
import com.devhub.backend.service.SubscriptionService;
import com.devhub.backend.service.UserService;
import com.devhub.backend.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/subscription")
@CrossOrigin(origins = "http://localhost:3000")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final UserService userService;
    private final ApiService apiService;
    private final JwtUtil jwtUtil;

    public SubscriptionController(SubscriptionService subscriptionService,
                                   UserService userService,
                                   ApiService apiService,
                                   JwtUtil jwtUtil) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
        this.apiService = apiService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/subscribe/{apiId}")
    public ResponseEntity<?> subscribe(@PathVariable Long apiId,
                                   @RequestHeader("Authorization") String token) {
        try {
            String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
            User user = userService.getUserByEmail(email);
            Api api = apiService.getApiById(apiId);
            Subscription subscription = subscriptionService.subscribe(user, api);
            return ResponseEntity.ok(subscription);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping("/my-subscriptions")
    public ResponseEntity<?> getMySubscriptions(@RequestHeader("Authorization") String token) {
        try {
            String email = jwtUtil.extractEmail(token.replace("Bearer ", ""));
            User user = userService.getUserByEmail(email);
            List<Subscription> subscriptions = subscriptionService.getUserSubscriptions(user);
            return ResponseEntity.ok(subscriptions);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage()));
        }
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
