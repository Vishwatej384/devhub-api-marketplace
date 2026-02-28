package com.devhub.backend.repository;

import com.devhub.backend.entity.Api;
import com.devhub.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApiRepository extends JpaRepository<Api, Long> {
    List<Api> findByCreatedBy(User user);
}
