package com.elarslan.crudmongo.service;


import com.elarslan.crudmongo.model.Footballer;
import com.elarslan.crudmongo.service.base.IService;
import org.springframework.stereotype.Service;

/**
 * Created by ersin on 16.11.2019.
 */
@Service
public interface FootballerService extends IService<Footballer, Long> {

    Footballer save(Footballer footballer);

    Footballer findBySurname(String surname);

    void deleteById(Long id);

    Footballer findAllByOrderByWorthDesc();

    Footballer findFootballerByMaxWorth();
}
