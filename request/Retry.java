package com.example.lenovo.myapplication.request;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by lenovo on 2018/8/15.
 * auther:lenovo
 * Date：2018/8/15
 */
@Documented
@Retention(RUNTIME)
@Target(METHOD)
public @interface Retry {
    int count() default 0;
}
