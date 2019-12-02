package com.example.demo.annotation;

import java.lang.annotation.*;

/**
 * Created by GEEX177 on 2019/10/24.
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Inherited
public @interface AopLogs {

    String value() default "";
}
