package com.devhubsocial.microservices.core.developer.services;

import com.devhubsocial.api.core.developer.Developer;
import com.devhubsocial.api.core.developer.DeveloperService;
import com.devhubsocial.microservices.core.developer.entity.DeveloperEntity;
import com.devhubsocial.microservices.core.developer.entity.DeveloperRepository;
import com.devhubsocial.util.exceptions.InvalidInputException;
import com.devhubsocial.util.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RestController;
import com.devhubsocial.util.http.ServiceUtil;

@RestController
public class DeveloperServiceImpl implements DeveloperService {

    private static final Logger LOG = LoggerFactory.getLogger(DeveloperServiceImpl.class);

    private final ServiceUtil serviceUtil;

    private final DeveloperRepository developerRepository;

    private final DeveloperMapper mapper;



    @Autowired
    public DeveloperServiceImpl(DeveloperRepository developerRepository, DeveloperMapper mapper, ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
        this.developerRepository = developerRepository;
        this.mapper = mapper;
    }

    @Override
    public Developer createDeveloper(Developer body) {
        try {
            DeveloperEntity developerEntity = mapper.apiToEntity(body);
            DeveloperEntity newEntity = developerRepository.save(developerEntity);

            LOG.debug("createDeveloper: entity created for developerId: {}", body.getDeveloperId());
            return mapper.entityToApi(newEntity);

        } catch (DuplicateKeyException dke) {
            throw new InvalidInputException("Duplicate key, Developer Id: " + body.getDeveloperId());
        }
    }

    @Override
    public Developer getDeveloper(int developerId) {
        if (developerId < 1) throw new InvalidInputException("Invalid developerId: " + developerId);

        DeveloperEntity developerEntity = developerRepository.findByDeveloperId(developerId)
                .orElseThrow(() -> new NotFoundException("No developer found for developerId: " + developerId));

        Developer response = mapper.entityToApi(developerEntity);
        response.setServiceAddress(serviceUtil.getServiceAddress());

        LOG.debug("getDeveloper: found developerId: {}", response.getDeveloperId());

        return response;
    }

    @Override
    public void deleteDeveloper(int developerId) {
        LOG.debug("deleteDeveloper: tries to delete an entity with developerId: {}", developerId);
        developerRepository.findByDeveloperId(developerId).ifPresent(e -> developerRepository.delete(e));
    }
}
