package com.noob.validate.annotation;

import java.lang.annotation.*;

/**
 * validate number decimal location
 * Created by xiongwenjun on 2016/11/10.
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Decimals {

    int position() default 0;
}
