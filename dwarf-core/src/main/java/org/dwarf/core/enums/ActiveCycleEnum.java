package org.dwarf.core.enums;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/19 10:49
 * @Description: 执行的生命周期
 **/
public enum ActiveCycleEnum {

    /**
     * 尚未运行
     */
    NOT_RUN,
    /**
     * 正在运行
     */
    IS_RUN,
    /**
     * 正常结束
     */
    NORMAL_END,
    /**
     * 报错
     */
    EXCEPTION

}
