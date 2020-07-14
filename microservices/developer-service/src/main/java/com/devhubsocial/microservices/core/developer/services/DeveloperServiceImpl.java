package com.devhubsocial.microservices.core.developer.services;

import com.devhubsocial.api.core.developer.Developer;
import com.devhubsocial.api.core.developer.DeveloperService;
import com.devhubsocial.util.exceptions.InvalidInputException;
import com.devhubsocial.util.exceptions.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.devhubsocial.util.http.ServiceUtil;

@RestController
public class DeveloperServiceImpl implements DeveloperService {

    private static final Logger LOG = LoggerFactory.getLogger(DeveloperServiceImpl.class);

    private final ServiceUtil serviceUtil;

    @Autowired
    public DeveloperServiceImpl(ServiceUtil serviceUtil) {
        this.serviceUtil = serviceUtil;
    }

    @Override
    public Developer getDeveloper(int developerId) {
        LOG.debug("/developer return the found developer for developerId={}", developerId);

        if (developerId < 1) throw new InvalidInputException("Invalid developerId: " + developerId);

        if (developerId == 13) throw new NotFoundException("No developer found for developerId: " + developerId);
        return new Developer(1, "Sam", "Smith", "Python Developer", false, "", serviceUtil.getServiceAddress());
    }
}
