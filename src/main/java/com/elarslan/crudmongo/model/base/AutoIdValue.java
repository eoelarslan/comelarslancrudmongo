package com.elarslan.crudmongo.model.base;


import lombok.Getter;
import org.springframework.data.annotation.Id;


public class AutoIdValue {

    @Getter
    @Id
    protected String id;


}
