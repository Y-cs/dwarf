package org.dwarf.core.config;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 14:27
 * @Description:
 **/
public interface RecordRootObject {

    static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 操作人Id
     *
     * @return 操作人对象
     */
    RecordOperator operator();

    /**
     * 时间
     *
     * @return
     */
    default String now() {
        return FORMATTER.format(LocalDateTime.now());
    }

    /**
     * 时间
     *
     * @param pattern
     * @return
     */
    default String now(String pattern) {
        return DateTimeFormatter.ofPattern(pattern).format(LocalDateTime.now());
    }

    /**
     * username
     *
     * @return
     */
    default String username() {
        return operator().name();
    }

    /**
     * userid
     *
     * @return
     */
    default long userid() {
        return operator().id();
    }

}
