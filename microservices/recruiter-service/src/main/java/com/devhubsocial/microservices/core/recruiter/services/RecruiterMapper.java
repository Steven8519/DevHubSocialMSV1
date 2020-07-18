package com.devhubsocial.microservices.core.recruiter.services;

import com.devhubsocial.api.core.recruiter.Recruiter;
import com.devhubsocial.microservices.core.recruiter.persistence.RecruiterEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RecruiterMapper {
    @Mappings({
            @Mapping(target = "companyRating", source="entity.companyRating"),
            @Mapping(target = "serviceAddress", ignore = true)
    })
    Recruiter entityToApi(RecruiterEntity entity);

    @Mappings({
            @Mapping(target = "companyRating", source="api.companyRating"),
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    RecruiterEntity apiToEntity(Recruiter api);

    List<Recruiter> entityListToApiList(List<RecruiterEntity> entity);
    List<RecruiterEntity> apiListToEntityList(List<Recruiter> api);
}
