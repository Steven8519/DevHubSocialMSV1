package com.devhubsocial.microservices.core.recruiter.services;

import com.devhubsocial.api.core.recruiter.Recruiter;
import com.devhubsocial.api.core.recruiter.RecruiterService;
import com.devhubsocial.microservices.core.recruiter.persistence.RecruiterEntity;
import com.devhubsocial.microservices.core.recruiter.persistence.RecruiterRepository;
import com.devhubsocial.util.exceptions.InvalidInputException;
import com.devhubsocial.util.http.ServiceUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RecruiterServiceImpl implements RecruiterService {
    private static final Logger LOG = LoggerFactory.getLogger(RecruiterServiceImpl.class);

    private final RecruiterRepository recruiterRepository;

    private final RecruiterMapper mapper;

    private final ServiceUtil serviceUtil;

    @Autowired
    public RecruiterServiceImpl(RecruiterRepository recruiterRepository, RecruiterMapper mapper, ServiceUtil serviceUtil) {
        this.recruiterRepository = recruiterRepository;
        this.mapper = mapper;
        this.serviceUtil = serviceUtil;
    }


    @Override
    public Recruiter createRecruiter(Recruiter body) {
        try {
            RecruiterEntity recruiterEntity = mapper.apiToEntity(body);
            RecruiterEntity newEntity = recruiterRepository.save(recruiterEntity);

            LOG.debug("createRecruiter: created a recruiter entity: {}/{}", body.getDeveloperId(), body.getRecruiterId());
            return mapper.entityToApi(newEntity);

        } catch (DuplicateKeyException dke) {
            throw new InvalidInputException("Duplicate key, Developer Id: " + body.getDeveloperId() + ", Recruiter Id:" + body.getRecruiterId());
        }
    }

    @Override
    public List<Recruiter> getRecruiters(int developerId) {
        if (developerId < 1) throw new InvalidInputException("Invalid developerId: " + developerId);

        List<RecruiterEntity> entityList = recruiterRepository.findByDeveloperId(developerId);
        List<Recruiter> list = mapper.entityListToApiList(entityList);
        list.forEach(e -> e.setServiceAddress(serviceUtil.getServiceAddress()));

        LOG.debug("getRecruiters: response size: {}", list.size());

        return list;
    }

    @Override
    public void deleteRecruiters(int developerId) {
        LOG.debug("deleteRecruiters: tries to delete recruiters for the developer with developerId: {}", developerId);
        recruiterRepository.deleteAll(recruiterRepository.findByDeveloperId(developerId));
    }
}
