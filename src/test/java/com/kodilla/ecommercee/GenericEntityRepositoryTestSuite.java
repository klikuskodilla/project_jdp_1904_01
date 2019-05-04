package com.kodilla.ecommercee;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GenericEntityRepositoryTestSuite {

    @Autowired
    private GenericEntityRepository repository;

    public static final String VALUE = "test value";

    @Test
    public void testGenericEntityRepositorySave() {
        //Given
        GenericEntity entity = new GenericEntity(VALUE);

        //When
        repository.save(entity);

        //Then
        Long id = entity.getId();
        Optional<GenericEntity> genericEntity = repository.findById(id);
        Assert.assertTrue(genericEntity.isPresent());

        //Clean up
        repository.deleteById(id);
    }
}
