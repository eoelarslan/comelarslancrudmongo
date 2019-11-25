package com.elarslan.crudmongo.service.base;


import com.elarslan.crudmongo.model.base.DataEntity;

import java.io.Serializable;
import java.util.Optional;
import java.util.stream.Stream;

public interface IService<T extends DataEntity, ID extends Serializable> {

    public T save(T entity);

    public Stream<T> findAll();

    public Optional<T> get(ID id);

    public void remove(ID id);
}
