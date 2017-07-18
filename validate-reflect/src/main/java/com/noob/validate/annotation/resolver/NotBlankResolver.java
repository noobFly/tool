package com.noob.validate.annotation.resolver;

import java.util.Collection;
import java.util.Map;

import com.noob.validate.abstracts.AbstractResolver;


public class NotBlankResolver extends AbstractResolver {

    public void validate(Object j) throws Exception {
        boolean result = j != null;
        if (result) {
            if (j instanceof String) {
                String str = String.class.cast(j);
                result = str.trim().length() > 0;
            } else if (j instanceof Collection) {
                Collection collection = Collection.class.cast(j);
                result = collection.size() > 0;
            } else if (j instanceof Map) {
                Map map = Map.class.cast(j);
                result = map.size() > 0;
            }
        }

        if (!result) {
            throw new Exception("notBlank validate fail.");
        }
    }

}
