package com.kodilla.ecommercee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface GenericEntityRepository extends JpaRepository<GenericEntity, Long> {

    List<GenericEntity> findByValue(String value);
}
