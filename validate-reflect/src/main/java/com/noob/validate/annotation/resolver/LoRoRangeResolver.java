package com.noob.validate.annotation.resolver;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.noob.validate.abstracts.AbstractRangeResolver;
import com.noob.validate.annotation.LoRoRange;

/**
 * Created by xiongwenjun on 2016/8/12.
 */
public class LoRoRangeResolver extends AbstractRangeResolver {


    @Override
    public void rangeValidate(Field f, Object obj) throws Exception {
        LoRoRange annotation = f.getAnnotation(LoRoRange.class);
        validate(obj instanceof BigDecimal ? BigDecimal.class.cast(obj).doubleValue() : Double.parseDouble(String.valueOf(obj)),
                annotation.min(), annotation.max());
    }

    public boolean judgeMaxRange(String max, double doubleValue) {
        return doubleValue < Double.parseDouble(max);
    }

    public boolean judgeMinRange(String min, double doubleValue) {
        return doubleValue > Double.parseDouble(min);
    }

}
