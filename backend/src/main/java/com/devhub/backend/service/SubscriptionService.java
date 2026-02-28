package com.devhub.backend.service;

import com.devhub.backend.entity.Api;
import com.devhub.backend.entity.Subscription;
import com.devhub.backend.entity.User;
import com.devhub.backend.repository.SubscriptionRepository;
import com.devhub.backend.repository.UserRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;
    private final UserRepository userRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository,
                              UserRepository userRepository) {
        this.subscriptionRepository = subscriptionRepository;
        this.userRepository = userRepository;
    }

    public Subscription subscribe(User user, Api api) {
        Subscription existing = subscriptionRepository.findByUserAndApi(user, api);
        if (existing != null) {
            throw new RuntimeException("Already subscribed to this API");
        }
        Subscription subscription = new Subscription();
        subscription.setUser(user);
        subscription.setApi(api);
        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getUserSubscriptions(User user) {
        return subscriptionRepository.findByUser(user);
    }

    public Subscription getByApiKey(String apiKey) {
        return subscriptionRepository.findByApiKey(apiKey);
    }

    public boolean isUserSubscribed(String userEmail, Long apiId) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            return false;
        }
        
        List<Subscription> subscriptions = subscriptionRepository.findByUser(user);
        return subscriptions.stream()
                .anyMatch(sub -> sub.getApi().getId().equals(apiId));
    }
}
