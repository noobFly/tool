package com.noob.validate.annotation.resolver;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import com.noob.validate.abstracts.IResolver;
import com.noob.validate.annotation.Decimals;

/**
 * Created by xiongwenjun on 2016/11/10.
 */
public class DecimalsResolver implements IResolver {
    public <T> void validate(T j, Field f) throws Exception {
        Object obj = f.get(j);
        if (obj != null && obj instanceof Number) {
            String value = obj instanceof BigDecimal ? BigDecimal.class.cast(obj).toPlainString() : String.valueOf(obj);
            int position = Math.abs(f.getAnnotation(Decimals.class).position());// 鏍￠獙浣嶆暟
            StringBuffer regex = new StringBuffer("^(([1-9]\\d*)|0)");
            if (position > 0) {
                regex.append("(\\.(\\d){1,").append(position).append("})?$");// 鏍￠獙瑙勫垯
            } else {
                regex.append("(\\.{0})$"); // 榛樿涓�0浣�
            }
            boolean result = value.matches(regex.toString());
            if (!result) {
                throw new Exception("decimals validate fail.");
            }
        }

    }
}
