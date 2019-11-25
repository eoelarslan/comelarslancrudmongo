package com.elarslan.crudmongo.model;


import com.elarslan.crudmongo.model.base.DataEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class Footballer extends DataEntity {

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Surname cannot be empty")
    private String surname;

    @NotNull(message = "Date of birth cannot be empty")
    private LocalDate dateOfBirth;

    @NotEmpty(message = "Date of place cannot be empty")
    private String dateofPlace;

    @NotEmpty(message = "National cannot be empty")
    private String national;

    @NotNull(message = "Worth cannot be empty")
    private int worth;
}
