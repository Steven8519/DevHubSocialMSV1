package com.devhubsocial.microservices.core.developer;

import com.devhubsocial.microservices.core.developer.entity.DeveloperEntity;
import com.devhubsocial.microservices.core.developer.entity.DeveloperRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PersistenceTests {
    @Autowired
    private DeveloperRepository developerRepository;

    private DeveloperEntity savedEntity;


    @Before
    public void setUp() {
        developerRepository.deleteAll();
        DeveloperEntity developerEntity = new DeveloperEntity(1, "Sam", "Hanks",
                "Java Developer", true, "Peak 6");

        savedEntity = developerRepository.save(developerEntity);
        assertEqualsDeveloper(developerEntity, savedEntity);
    }

    @Test
    public void create() {

        DeveloperEntity newEntity = new DeveloperEntity(2, "Lisa", "Monroe",
                "Full Stack JavaScript Developer", true, "TCF");
        developerRepository.save(newEntity);

        DeveloperEntity foundEntity = developerRepository.findById(newEntity.getId()).get();
        assertEqualsDeveloper(newEntity, foundEntity);

        assertEquals(2, developerRepository.count());
    }

    @Test
    public void update() {
        savedEntity.setFirstName("Tim");
        developerRepository.save(savedEntity);

        DeveloperEntity foundEntity = developerRepository.findById(savedEntity.getId()).get();
        assertEquals(1, (long)foundEntity.getVersion());
        assertEquals("Tim", foundEntity.getFirstName());
    }

    @Test
    public void delete() {
        developerRepository.delete(savedEntity);
        assertFalse(developerRepository.existsById(savedEntity.getId()));
    }

    @Test
    public void getByDeveloperId() {
        Optional<DeveloperEntity> entity = developerRepository.findByDeveloperId(savedEntity.getDeveloperId());

        assertTrue(entity.isPresent());
        assertEqualsDeveloper(savedEntity, entity.get());
    }

    @Test
    public void optimisticLockError() {

        // Store the saved entity in two separate entity objects
        DeveloperEntity entity1 = developerRepository.findById(savedEntity.getId()).get();
        DeveloperEntity entity2 = developerRepository.findById(savedEntity.getId()).get();

        // Update the entity using the first entity object
        entity1.setFirstName("Jason");
        developerRepository.save(entity1);

        //  Update the entity using the second entity object.
        // This should fail since the second entity now holds a old version number, i.e. a Optimistic Lock Error
        try {
            entity2.setFirstName("Kim");
            developerRepository.save(entity2);

            fail("Expected an OptimisticLockingFailureException");
        } catch (OptimisticLockingFailureException e) {}

        // Get the updated entity from the database and verify its new sate
        DeveloperEntity updatedEntity = developerRepository.findById(savedEntity.getId()).get();
        assertEquals(1, (int)updatedEntity.getVersion());
        assertEquals("Jason", updatedEntity.getFirstName());
    }

    private void assertEqualsDeveloper(DeveloperEntity expectedEntity, DeveloperEntity actualEntity) {
        assertEquals(expectedEntity.getId(),               actualEntity.getId());
        assertEquals(expectedEntity.getVersion(),          actualEntity.getVersion());
        assertEquals(expectedEntity.getDeveloperId(),        actualEntity.getDeveloperId());
        assertEquals(expectedEntity.getFirstName(),           actualEntity.getFirstName());
        assertEquals(expectedEntity.getLastName(),           actualEntity.getLastName());
        assertEquals(expectedEntity.getTypeOfDeveloper(),           actualEntity.getTypeOfDeveloper());
        assertEquals(expectedEntity.getCompanyName(),           actualEntity.getCompanyName());
    }
}
