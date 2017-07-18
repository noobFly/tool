package com.noob.validate.annotation;

import java.lang.annotation.*;

/**
 *
 *   double_min <= double_value <= double_max, if min/max is "" then pass
 */
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LcRcRange {

    String min() default "";

    String max() default "";
}
