package org.dwarf.core.ano;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 15:02
 * @Description:
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface Record {

    String success();

    String fail() default "";

    String condition() default "";

    String operator() default "";

    String businessCode() default "";

}
