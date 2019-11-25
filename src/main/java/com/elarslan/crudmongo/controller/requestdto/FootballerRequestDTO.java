package com.elarslan.crudmongo.controller.requestdto;


import com.elarslan.crudmongo.model.base.DataEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by ersin on 24.11.2019.
 */
@ToString
@Getter
@Setter
public class FootballerRequestDTO extends DataEntity {
    private int worth;
}
