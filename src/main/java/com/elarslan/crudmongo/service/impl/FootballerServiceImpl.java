package com.elarslan.crudmongo.service.impl;


import com.elarslan.crudmongo.model.Footballer;
import com.elarslan.crudmongo.repository.IFootballerRepository;
import com.elarslan.crudmongo.service.FootballerService;
import com.elarslan.crudmongo.service.base.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by ersin on 16.11.2019.
 */
@Service
public class FootballerServiceImpl implements FootballerService {

    @Autowired
    IFootballerRepository footballerRepository;


    @Override
    public List<Footballer> findByName(String name) {
        return footballerRepository.findByName(name);
    }

    @Override
    public Footballer save(Footballer footballer) {
        return footballerRepository.insert(footballer);
    }

    @Override
    public List<Footballer> findBySurname(String surname) {
        return footballerRepository.findBySurname(surname);
    }

    @Override
    public List<Footballer> findAll() {
        return footballerRepository.findAll();
    }

    @Override
    public Footballer findMostValuableFootballer() {
        return footballerRepository.findFirstByOrderByWorthDesc();
    }

    @Override
    public Footballer findByNameAndSurname(String name, String surname) {
        return footballerRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public Footballer updateFootballer(Footballer footballer) {
        return footballerRepository.save(footballer);
    }

    @Override
    public void deleteFootballerByNameAndSurname(String name, String surname) {
        footballerRepository.deleteFootballerByNameAndSurname(name, surname);
    }
}
