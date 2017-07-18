package com.noob.validate.annotation.resolver;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.noob.validate.abstracts.AbstractRangeResolver;
import com.noob.validate.annotation.LcRcRange;

/**
 * Created by xiongwenjun on 2016/8/12.
 */
public class LcRcRangeResolver extends AbstractRangeResolver {

    @Override
    public void rangeValidate(Field f, Object obj) throws Exception {
        LcRcRange annotation = f.getAnnotation(LcRcRange.class);
        validate(obj instanceof BigDecimal ? BigDecimal.class.cast(obj).doubleValue() : Double.parseDouble(String.valueOf(obj)),
                annotation.min(), annotation.max());
    }


    @Override
    public boolean judgeMinRange(String min, double doubleValue) {
        return doubleValue >= Double.parseDouble(min);
    }

    @Override
    public boolean judgeMaxRange(String max, double doubleValue) {
        return doubleValue <= Double.parseDouble(max);
    }

}
