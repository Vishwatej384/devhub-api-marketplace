package com.devhub.backend.repository;

import com.devhub.backend.entity.Api;
import com.devhub.backend.entity.Subscription;
import com.devhub.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    List<Subscription> findByUser(User user);
    Subscription findByUserAndApi(User user, Api api);
    Subscription findByApiKey(String apiKey);
}
