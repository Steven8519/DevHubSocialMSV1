package com.devhubsocial.microservices.core.recruiter.persistence;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecruiterRepository extends CrudRepository<RecruiterEntity, String> {
    List<RecruiterEntity> findByDeveloperId(int developerId);
}
