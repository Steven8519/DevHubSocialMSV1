package com.devhubsocial.microservices.core.developer;

import com.devhubsocial.api.core.developer.Developer;
import com.devhubsocial.microservices.core.developer.entity.DeveloperEntity;
import com.devhubsocial.microservices.core.developer.services.DeveloperMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.Assert.*;

public class MapperTests {
    private DeveloperMapper mapper = Mappers.getMapper(DeveloperMapper.class);

    @Test
    public void mapperTests() {

        assertNotNull(mapper);

        Developer api = new Developer(1, "Mike", "Hamilton", "python",
                false, "Travis Inc", "asd");

        DeveloperEntity developerEntity = mapper.apiToEntity(api);

        assertEquals(api.getDeveloperId(), developerEntity.getDeveloperId());
        assertEquals(api.getDeveloperId(), developerEntity.getDeveloperId());
        assertEquals(api.getFirstName(), developerEntity.getFirstName());
        assertEquals(api.getLastName(), developerEntity.getLastName());
        assertEquals(api.getTypeOfDeveloper(), developerEntity.getTypeOfDeveloper());
        assertEquals(api.getCompanyName(), developerEntity.getCompanyName());

        Developer api2 = mapper.entityToApi(developerEntity);

        assertEquals(api.getDeveloperId(), api2.getDeveloperId());
        assertEquals(api.getDeveloperId(), api2.getDeveloperId());
        assertEquals(api.getFirstName(),      api2.getFirstName());
        assertEquals(api.getLastName(),    api2.getLastName());
        assertEquals(api.getTypeOfDeveloper(),    api2.getTypeOfDeveloper());
        assertEquals(api.getCompanyName(),    api2.getCompanyName());
        assertNull(api2.getServiceAddress());
    }
}
