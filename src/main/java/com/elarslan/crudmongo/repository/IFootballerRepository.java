package com.elarslan.crudmongo.repository;


import com.elarslan.crudmongo.model.Footballer;
import com.elarslan.crudmongo.repository.base.IDataRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by ersin on 25.11.2019.
 */
@Repository
public interface IFootballerRepository extends IDataRepository<Footballer, Long> {
    Footballer findBySurname(String surname);
    Footballer findTopByOrderByWorthDesc();

}
