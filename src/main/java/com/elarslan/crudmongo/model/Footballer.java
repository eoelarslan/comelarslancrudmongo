package com.elarslan.crudmongo.model;


import com.elarslan.crudmongo.model.base.DataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * Created by ersin on 24.11.2019.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("footballer")
@CompoundIndex(def = "{'name':1, 'surname':-1}", name = "compound_index", unique = true)
public class Footballer extends DataEntity {

    @NotBlank(message = "Name cannot be empty")
    private String name;

    @NotBlank(message = "Surname cannot be empty")
    private String surname;

    @NotNull(message = "Date of birth cannot be empty")
    private LocalDate dateOfBirth;

    @NotBlank(message = "Date of place cannot be empty")
    private String dateofPlace;

    @NotBlank(message = "National cannot be empty")
    private String national;

    @NotNull(message = "Worth cannot be empty")
    private int worth;
}
