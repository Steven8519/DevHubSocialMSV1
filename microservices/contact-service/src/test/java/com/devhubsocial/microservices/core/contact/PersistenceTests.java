package com.devhubsocial.microservices.core.contact;

import com.devhubsocial.microservices.core.contact.entity.ContactEntity;
import com.devhubsocial.microservices.core.contact.entity.ContactRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.springframework.transaction.annotation.Propagation.NOT_SUPPORTED;

@RunWith(SpringRunner.class)
@DataJpaTest
@Transactional(propagation = NOT_SUPPORTED)
public class PersistenceTests {

    @Autowired
    private ContactRepository contactRepository;

    private ContactEntity saveEntity;

    @Before
    public void setUp() {
        contactRepository.deleteAll();
        ContactEntity contactEntity = new ContactEntity(1, 2, 3, "555-555-5555", "ag1@gmail.com");
        saveEntity = contactRepository.save(contactEntity);
        assertEqualsContact(contactEntity, saveEntity);
    }

    @Test
    public void create() {

        ContactEntity newEntity = new ContactEntity(2, 3, 4, "777-343-8888", "ag2@gmail.com");
        contactRepository.save(newEntity);

        ContactEntity foundEntity = contactRepository.findById(newEntity.getId()).get();
        assertEqualsContact(newEntity, foundEntity);

        assertEquals(2, contactRepository.count());
    }

    @Test
    public void delete() {
        contactRepository.delete(saveEntity);
        assertFalse(contactRepository.existsById(saveEntity.getId()));
    }

    @Test
    public void getByDeveloperId() {
        List<ContactEntity> entityList = contactRepository.findByDeveloperId(saveEntity.getDeveloperId());

        assertThat(entityList, hasSize(1));
        assertEqualsContact(saveEntity, entityList.get(0));
    }

    @Test(expected = DataIntegrityViolationException.class)
    public void duplicateError() {
        ContactEntity entity = new ContactEntity(1, 2, 3, "777-343-8888", "ag2@gmail.com");
        contactRepository.save(entity);
    }

    @Test
    public void optimisticLockError() {

        // Store the saved entity in two separate entity objects
        ContactEntity entity1 = contactRepository.findById(saveEntity.getId()).get();
        ContactEntity entity2 = contactRepository.findById(saveEntity.getId()).get();

        // Update the entity using the first entity object
        entity1.setPhoneNumber("ag1@gmail.com");
        contactRepository.save(entity1);

        //  Update the entity using the second entity object.
        // This should fail since the second entity now holds a old version number, i.e. a Optimistic Lock Error
        try {
            entity2.setEmail("ag4@gmail.com");
            contactRepository.save(entity2);

            fail("Expected an OptimisticLockingFailureException");
        } catch (OptimisticLockingFailureException e) {}

        // Get the updated entity from the database and verify its new sate
        ContactEntity updatedEntity = contactRepository.findById(saveEntity.getId()).get();
        assertEquals(1, (int)updatedEntity.getVersion());
        assertEquals("ag1@gmail.com", updatedEntity.getEmail());
    }

    private void assertEqualsContact(ContactEntity expectedEntity, ContactEntity actualEntity) {
        assertEquals(expectedEntity.getId(),        actualEntity.getId());
        assertEquals(expectedEntity.getVersion(),   actualEntity.getVersion());
        assertEquals(expectedEntity.getDeveloperId(), actualEntity.getDeveloperId());
        assertEquals(expectedEntity.getContactId(),  actualEntity.getContactId());
        assertEquals(expectedEntity.getRecruiterId(),    actualEntity.getRecruiterId());
        assertEquals(expectedEntity.getPhoneNumber(),   actualEntity.getPhoneNumber());
        assertEquals(expectedEntity.getEmail(),   actualEntity.getEmail());
    }
}
