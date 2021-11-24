package org.dwarf.core.config;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 16:20
 * @Description:
 **/
public enum ParameterKey {

    /**
     * 错误
     */
    EXCEPTION("exception"),
    /**
     * 结果
     */
    RESULT("result"),

    /**
     * 类
     */
    CLASS("class"),

    /**
     * 方法
     */
    METHOD("method"),

    ;

    /**
     * key
     */
    private String key;

    ParameterKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
