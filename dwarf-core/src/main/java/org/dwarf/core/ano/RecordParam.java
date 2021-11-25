package org.dwarf.core.ano;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 15:14
 * @Description: 是否可以穿透方法
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER})
public @interface RecordParam {
    /**
     * 别名
     *
     * @return 别名
     */
    String name() default "";

    /**
     * 是否穿透
     *
     * @return
     */
    boolean isAcross() default false;
}
