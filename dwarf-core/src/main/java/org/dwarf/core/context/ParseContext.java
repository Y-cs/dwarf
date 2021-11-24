package org.dwarf.core.context;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 10:43
 * @Description:
 **/
@Data
public class ParseContext {

    private String metaMessage;

    private List<String> subMessage;

    private List<String> spelExpression;

    private Map<String, Object> parameter;

    private Map<String, Object> acrossParameter;

    private String resultMessage;

    private Object rootObject;

    private Map<String, Object> analyticValue;

}
