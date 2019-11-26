package com.elarslan.crudmongo.repository;


import com.elarslan.crudmongo.model.Footballer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by ersin on 25.11.2019.
 */
@Repository
public interface IFootballerRepository extends MongoRepository<Footballer, Long> {
    List<Footballer> findByName(String name);

    List<Footballer> findBySurname(String surname);

    Footballer findFirstByOrderByWorthDesc();
}
