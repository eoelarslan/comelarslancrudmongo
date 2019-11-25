package com.elarslan.crudmongo.repository.base;



import com.elarslan.crudmongo.model.base.DataEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface IDataRepository<T extends DataEntity, ID extends Serializable> extends MongoRepository<T, ID> {
}
