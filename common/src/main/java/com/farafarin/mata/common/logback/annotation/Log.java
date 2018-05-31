package com.farafarin.mata.common.logback.annotation;

import java.lang.annotation.*;

//import static java.lang.annotation.ElementType.FIELD;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by Asus on 11/5/2017.
 */

@Retention(RUNTIME)
@Target(FIELD)
@Documented
public @interface Log {
}
