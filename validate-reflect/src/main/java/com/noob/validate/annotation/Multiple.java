package com.noob.validate.annotation;

import java.lang.annotation.*;

/**
 * if value % radix == 0 retun true
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Multiple {

    int radix() default 1;
}
