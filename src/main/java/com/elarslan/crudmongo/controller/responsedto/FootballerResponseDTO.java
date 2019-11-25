package com.elarslan.crudmongo.controller.responsedto;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Getter
@Setter
@ToString
@Component
public class FootballerResponseDTO {

    private String name;
    private String surname;
}
