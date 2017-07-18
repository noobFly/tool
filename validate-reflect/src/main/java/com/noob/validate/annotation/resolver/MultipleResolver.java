package com.noob.validate.annotation.resolver;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.noob.validate.abstracts.IResolver;
import com.noob.validate.annotation.Multiple;

/**
 * Created by xiongwenjun on 2016/11/10.
 */
public class MultipleResolver implements IResolver {
    @Override
    public <T> void validate(T j, Field f) throws Exception {
        Object obj = f.get(j);

        if (obj != null && obj instanceof Number) {
            Multiple annotation = f.getAnnotation(Multiple.class);
            int radix = annotation.radix();
            if (Math.abs(radix) > 1) {
                validate(obj instanceof BigDecimal ?
                        BigDecimal.class.cast(obj).doubleValue() : Double.parseDouble(String.valueOf(obj)), radix);
            }

        }
    }

    public void validate(double value, double radix) throws Exception {
        if (value % radix != 0) {
            throw new Exception("multiple validate fail.");
        }

    }
}

