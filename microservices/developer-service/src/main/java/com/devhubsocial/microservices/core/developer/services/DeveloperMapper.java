package com.devhubsocial.microservices.core.developer.services;

import com.devhubsocial.api.core.developer.Developer;
import com.devhubsocial.microservices.core.developer.entity.DeveloperEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface DeveloperMapper {

    @Mappings({
            @Mapping(target = "serviceAddress", ignore = true)
    })
    Developer entityToApi(DeveloperEntity entity);

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    DeveloperEntity apiToEntity(Developer api);
}
