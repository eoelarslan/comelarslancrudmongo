package com.elarslan.crudmongo.service.impl;


import com.elarslan.crudmongo.model.Footballer;
import com.elarslan.crudmongo.repository.IFootballerRepository;
import com.elarslan.crudmongo.service.FootballerService;
import com.elarslan.crudmongo.service.base.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by ersin on 16.11.2019.
 */
@Service
@Transactional
public class FootballerServiceImpl extends ServiceImpl<Footballer, Long> implements FootballerService {

    IFootballerRepository footballerRepository;

    public FootballerServiceImpl(IFootballerRepository footballerRepository) {
        super(footballerRepository);
        this.footballerRepository = footballerRepository;
    }

    @Override
    public Footballer findBySurname(String surname) {
        return footballerRepository.findBySurname(surname);
    }

    @Override
    public void deleteById(Long id) {
        footballerRepository.deleteById(id);

    }

    @Override
    public Footballer findAllByOrderByWorthDesc() {
        return null;
    }

    @Override
    public Footballer findFootballerByMaxWorth() {
        return footballerRepository.findTopByOrderByWorthDesc();
    }
}
