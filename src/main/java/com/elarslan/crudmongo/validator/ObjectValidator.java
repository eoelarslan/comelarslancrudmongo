package com.elarslan.crudmongo.validator;

import com.elarslan.crudmongo.model.Footballer;

import java.lang.reflect.Field;
import java.util.stream.Stream;

/**
 * Created by ersin on 28.11.2019.
 */
public class ObjectValidator {
    public boolean checkNull() throws IllegalAccessException {
/*        //Field.setAccessible();
        for (Field f : getClass().getDeclaredFields())
            f.setAccessible(true);
            if (f.get(this) != null)
                return false;
        return true;*/


        boolean valid = Stream.of(Footballer.class.getDeclaredFields())
                .filter(f -> !(f.getName().equals("fieldname allowed to be null") || f.getName().equals("d")))
                .allMatch(f -> {
                    f.setAccessible(true);
                    try {
                        return f.get(this) == null;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                });
        return false;
    }
}
