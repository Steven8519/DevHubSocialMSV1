package com.devhubsocial.microservices.core.recruiter;

import com.devhubsocial.microservices.core.recruiter.persistence.RecruiterEntity;
import com.devhubsocial.microservices.core.recruiter.persistence.RecruiterRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataMongoTest
public class PersistenceTest {
    @Autowired
    private RecruiterRepository repository;

    private RecruiterEntity savedEntity;

    @Before
    public void setupDb() {
        repository.deleteAll();

        RecruiterEntity entity = new RecruiterEntity(1, 2, "Emily", "Ranstad", 4);
        savedEntity = repository.save(entity);

        assertEqualsRecruiter(entity, savedEntity);
    }


    @Test
    public void create() {

        RecruiterEntity newEntity = new RecruiterEntity(1, 3, "Frank Thomas", "Judge Group", 3);
        repository.save(newEntity);

        RecruiterEntity foundEntity = repository.findById(newEntity.getId()).get();
        assertEqualsRecruiter(newEntity, foundEntity);

        assertEquals(2, repository.count());
    }

    @Test
    public void update() {
        savedEntity.setRecruiterName("Michael Haines");
        repository.save(savedEntity);

        RecruiterEntity foundEntity = repository.findById(savedEntity.getId()).get();
        assertEquals(1, (long)foundEntity.getVersion());
        assertEquals("Michael Haines", foundEntity.getRecruiterName());
    }

    @Test
    public void delete() {
        repository.delete(savedEntity);
        assertFalse(repository.existsById(savedEntity.getId()));
    }

    @Test
    public void getByDeveloperId() {
        List<RecruiterEntity> entityList = repository.findByDeveloperId(savedEntity.getDeveloperId());

        assertThat(entityList, hasSize(1));
        assertEqualsRecruiter(savedEntity, entityList.get(0));
    }

    @Test
    public void optimisticLockError() {

        // Store the saved entity in two separate entity objects
        RecruiterEntity entity1 = repository.findById(savedEntity.getId()).get();
        RecruiterEntity entity2 = repository.findById(savedEntity.getId()).get();

        // Update the entity using the first entity object
        entity1.setRecruiterName("James Thomas");
        repository.save(entity1);

        //  Update the entity using the second entity object.
        // This should fail since the second entity now holds a old version number, i.e. a Optimistic Lock Error
        try {
            entity2.setRecruiterName("John Thomas");
            repository.save(entity2);

            fail("Expected an OptimisticLockingFailureException");
        } catch (OptimisticLockingFailureException e) {}

        // Get the updated entity from the database and verify its new sate
        RecruiterEntity updatedEntity = repository.findById(savedEntity.getId()).get();
        assertEquals(1, (int)updatedEntity.getVersion());
        assertEquals("James Thomas", updatedEntity.getRecruiterName());
    }

    private void assertEqualsRecruiter(RecruiterEntity expectedEntity, RecruiterEntity actualEntity) {
        assertEquals(expectedEntity.getId(),               actualEntity.getId());
        assertEquals(expectedEntity.getVersion(),          actualEntity.getVersion());
        assertEquals(expectedEntity.getDeveloperId(),        actualEntity.getDeveloperId());
        assertEquals(expectedEntity.getRecruiterId(), actualEntity.getRecruiterId());
        assertEquals(expectedEntity.getRecruiterName(),           actualEntity.getRecruiterName());
        assertEquals(expectedEntity.getRecruitingAgency(),           actualEntity.getRecruitingAgency());
        assertEquals(expectedEntity.getCompanyRating(),          actualEntity.getCompanyRating());
    }

}
