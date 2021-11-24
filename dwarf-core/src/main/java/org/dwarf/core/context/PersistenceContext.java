package org.dwarf.core.context;

import lombok.Data;
import lombok.ToString;
import org.dwarf.core.config.RecordOperator;

import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 14:39
 * @Description: 持久化对象应知的上下文
 **/
@Data
public class PersistenceContext {

    private Map<String, Object> parameter;

    private String message;

    private boolean success;

    private RecordOperator operator;

    private Throwable throwable;

    private String businessCode;

}
