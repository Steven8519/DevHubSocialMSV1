package com.devhubsocial.microservices.core.contact;

import com.devhubsocial.api.core.contact.Contact;
import com.devhubsocial.microservices.core.contact.entity.ContactEntity;
import com.devhubsocial.microservices.core.contact.services.ContactMapper;
import org.junit.Test;
import org.mapstruct.factory.Mappers;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class MapperTests {

    private ContactMapper mapper = Mappers.getMapper(ContactMapper.class);

    @Test
    public void mapperTests() {

        assertNotNull(mapper);

        Contact api = new Contact(1, 2, 3, "765-424-8960", "sgman@gmail.com", "adr");

        ContactEntity contactEntity = mapper.apiToEntity(api);

        assertEquals(api.getDeveloperId(), contactEntity.getDeveloperId());
        assertEquals(api.getRecruiterId(), contactEntity.getRecruiterId());
        assertEquals(api.getContactId(), contactEntity.getContactId());
        assertEquals(api.getPhoneNumber(), contactEntity.getPhoneNumber());
        assertEquals(api.getEmail(), contactEntity.getEmail());

        Contact api2 = mapper.entityToApi(contactEntity);

        assertEquals(api.getDeveloperId(), api2.getDeveloperId());
        assertEquals(api.getRecruiterId(), api2.getRecruiterId());
        assertEquals(api.getContactId(), api2.getContactId());
        assertEquals(api.getPhoneNumber(), api2.getPhoneNumber());
        assertEquals(api.getEmail(), api2.getEmail());
        assertNull(api2.getServiceAddress());
    }

    @Test
    public void mapperListTests() {

        assertNotNull(mapper);

        Contact api = new Contact(1, 2, 3, "468-310-8160", "nhuson@gmail.com", "adr");
        List<Contact> apiList = Collections.singletonList(api);

        List<ContactEntity> entityList = mapper.apiListToEntityList(apiList);
        assertEquals(apiList.size(), entityList.size());

        ContactEntity contactEntity = entityList.get(0);

        assertEquals(api.getDeveloperId(), contactEntity.getDeveloperId());
        assertEquals(api.getRecruiterId(), contactEntity.getRecruiterId());
        assertEquals(api.getContactId(), contactEntity.getContactId());
        assertEquals(api.getPhoneNumber(), contactEntity.getPhoneNumber());
        assertEquals(api.getEmail(), contactEntity.getEmail());

        List<Contact> api2List = mapper.entityListToApiList(entityList);
        assertEquals(apiList.size(), api2List.size());

        Contact api2 = api2List.get(0);

        assertEquals(api.getDeveloperId(), api2.getDeveloperId());
        assertEquals(api.getRecruiterId(), api2.getRecruiterId());
        assertEquals(api.getContactId(), api2.getContactId());
        assertEquals(api.getPhoneNumber(), api2.getPhoneNumber());
        assertEquals(api.getEmail(), api2.getEmail());
        assertNull(api2.getServiceAddress());
    }
}
