package com.devhubsocial.microservices.core.developer.entity;

import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface DeveloperRepository extends PagingAndSortingRepository<DeveloperEntity, String> {
    Optional<DeveloperEntity> findByDeveloperId(int developerId);
}
