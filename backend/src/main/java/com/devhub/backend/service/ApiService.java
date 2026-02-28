package com.devhub.backend.service;

import com.devhub.backend.entity.Api;
import com.devhub.backend.entity.User;
import com.devhub.backend.repository.ApiRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ApiService {

    private final ApiRepository apiRepository;

    public ApiService(ApiRepository apiRepository) {
        this.apiRepository = apiRepository;
    }

    public Api createApi(Api api) {
        return apiRepository.save(api);
    }

    public List<Api> getAllApis() {
        return apiRepository.findAll();
    }

    public Api getApiById(Long id) {
        return apiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("API not found"));
    }

    public List<Api> getApisByUser(User user) {
        return apiRepository.findByCreatedBy(user);
    }

    public void deleteApi(Long id) {
        apiRepository.deleteById(id);
    }
}
