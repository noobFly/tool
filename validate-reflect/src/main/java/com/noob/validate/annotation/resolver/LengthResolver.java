package com.noob.validate.annotation.resolver;

import java.lang.reflect.Field;

import com.noob.validate.abstracts.IResolver;
import com.noob.validate.annotation.Length;

public class LengthResolver implements IResolver {

    public <T> void validate(T j, Field f) throws Exception {
        Object value = f.get(j);
        if (value != null && value instanceof String) {
            Length annotation = f.getAnnotation(Length.class);
            validate(String.valueOf(value), annotation.min(), annotation.max(),
                    annotation.trim());
        }

    }

    public void validate(String value, String min, String max, boolean isTrim)
            throws Exception {
        if (isTrim) {
            value = value.trim();
        }
        boolean result = true;
        int length = value.length();
        if (!EMPTY.equals(min)) {
            result = length >= Integer.parseInt(min);
        }
        if (result && !EMPTY.equals(max)) {
            result = length <= Integer.parseInt(max);
        }
        if (!result) {
            throw new Exception("length validate fail.");
        }

    }

}