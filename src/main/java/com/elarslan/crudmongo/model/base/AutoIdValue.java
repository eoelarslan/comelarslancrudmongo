package com.elarslan.crudmongo.model.base;


import lombok.Getter;
import org.springframework.data.annotation.Id;

import java.math.BigInteger;


public class AutoIdValue {

    @Getter
    @Id
    protected BigInteger id;


}
