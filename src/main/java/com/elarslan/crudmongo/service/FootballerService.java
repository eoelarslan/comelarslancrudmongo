package com.elarslan.crudmongo.service;


import com.elarslan.crudmongo.model.Footballer;

import java.util.List;

/**
 * Created by ersin on 26.11.2019.
 */

public interface FootballerService {
    List<Footballer> findByName(String name);

    Footballer save(Footballer footballer);

    List<Footballer> findBySurname(String surname);

    List<Footballer> findAll();

    Footballer findMostValuableFootballer();
}
