package com.devhubsocial.microservices.core.recruiter;

import com.devhubsocial.api.core.recruiter.Recruiter;
import com.devhubsocial.microservices.core.recruiter.persistence.RecruiterEntity;
import com.devhubsocial.microservices.core.recruiter.services.RecruiterMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MapperTests {
    private RecruiterMapper mapper = Mappers.getMapper(RecruiterMapper.class);

    @Test
    public void mapperTests() {

        assertNotNull(mapper);

        Recruiter api = new Recruiter(1, 2, "Christina Davis", "La Salle Network", 3, "adr");

        RecruiterEntity entity = mapper.apiToEntity(api);

        assertEquals(api.getDeveloperId(), entity.getDeveloperId());
        assertEquals(api.getRecruiterId(), entity.getRecruiterId());
        assertEquals(api.getRecruiterName(), entity.getRecruiterName());
        assertEquals(api.getRecruitingAgency(), entity.getRecruitingAgency());
        assertEquals(api.getCompanyRating(), entity.getCompanyRating());

        Recruiter api2 = mapper.entityToApi(entity);

        assertEquals(api.getDeveloperId(), api2.getDeveloperId());
        assertEquals(api.getRecruiterId(), api2.getRecruiterId());
        assertEquals(api.getRecruiterName(), api2.getRecruiterName());
        assertEquals(api.getRecruitingAgency(), api2.getRecruitingAgency());
        assertEquals(api.getCompanyRating(), api2.getCompanyRating());
        assertNull(api2.getServiceAddress());
    }

    @Test
    public void mapperListTests() {

        assertNotNull(mapper);

        Recruiter api = new Recruiter(1, 2, "Amanda McDonald", "Modis", 4, "adr");
        List<Recruiter> apiList = Collections.singletonList(api);

        List<RecruiterEntity> entityList = mapper.apiListToEntityList(apiList);
        assertEquals(apiList.size(), entityList.size());

        RecruiterEntity entity = entityList.get(0);

        assertEquals(api.getDeveloperId(), entity.getDeveloperId());
        assertEquals(api.getRecruiterId(), entity.getRecruiterId());
        assertEquals(api.getRecruiterName(), entity.getRecruiterName());
        assertEquals(api.getRecruitingAgency(), entity.getRecruitingAgency());
        assertEquals(api.getCompanyRating(), entity.getCompanyRating());

        List<Recruiter> api2List = mapper.entityListToApiList(entityList);
        assertEquals(apiList.size(), api2List.size());

        Recruiter api2 = api2List.get(0);

        assertEquals(api.getDeveloperId(), api2.getDeveloperId());
        assertEquals(api.getRecruiterId(), api2.getRecruiterId());
        assertEquals(api.getRecruiterName(), api2.getRecruiterName());
        assertEquals(api.getRecruitingAgency(), api2.getRecruitingAgency());
        assertEquals(api.getCompanyRating(), api2.getCompanyRating());
        assertNull(api2.getServiceAddress());
    }
}
