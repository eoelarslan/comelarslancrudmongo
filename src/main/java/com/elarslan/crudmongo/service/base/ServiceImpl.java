package com.elarslan.crudmongo.service.base;


import com.elarslan.crudmongo.model.base.DataEntity;
import com.elarslan.crudmongo.repository.base.IDataRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public class ServiceImpl<T extends DataEntity, ID extends Serializable> implements IService<T, ID> {

    @Autowired
    private IDataRepository<T, ID> dataRepository;

    public ServiceImpl(IDataRepository<T, ID> dataRepository) {
        this.dataRepository = dataRepository;
    }

    @Override
    public T save(T entity) {
        return dataRepository.insert(entity);
    }

    @Override
    public Stream<T> findAll() {
        return dataRepository.findAll().stream();
    }

    @Override
    public Optional<T> get(ID id) {
        return dataRepository.findById(id);
    }

    @Override
    public void remove(ID id) {
        dataRepository.deleteById(id);
    }
}
