package com.noob.validate.abstracts;

import java.lang.reflect.Field;

/**
 * Created by xiongwenjun on 2016/11/10.
 */
public abstract class AbstractRangeResolver implements IResolver {

    public <T> void validate(T j, Field f) throws Exception {
        Object obj = f.get(j);
        if (obj != null && obj instanceof Number) {
            rangeValidate(f, obj);
        }

    }


    public void validate(double value, String min, String max)
            throws Exception {
        boolean result = true;
        if (!EMPTY.equals(min)) {
            result = judgeMinRange(min, value);
        }
        if (result && !EMPTY.equals(max)) {
            result = judgeMaxRange(max, value);
        }
        if (!result) {
            throw new Exception("range validate fail.");
        }

    }

    public abstract void rangeValidate(Field f, Object obj) throws Exception;

    public abstract boolean judgeMaxRange(String max, double value);

    public abstract boolean judgeMinRange(String min, double value);
}
