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

    /**
     * 成功返回
     *
     * @return
     */
    String success();

    /**
     * 失败返回
     *
     * @return
     */
    String fail() default "";

    /**
     * 判断条件
     *
     * @return
     */
    String condition() default "";

    /**
     * 处理人
     *
     * @return
     */
    String operator() default "";

    /**
     * 业务编码
     *
     * @return
     */
    String businessCode() default "";

    /**
     * 扩展字段 意义在于成功或者失败都应该有的东西
     *
     * @return
     */
    String extendAttribute() default "";
}
