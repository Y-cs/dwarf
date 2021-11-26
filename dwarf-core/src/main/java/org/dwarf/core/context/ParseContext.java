package org.dwarf.core.context;

import lombok.Data;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.List;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 10:43
 * @Description:
 **/
@Data
public class ParseContext {
    /**
     * 原始信息
     */
    private String metaMessage;
    /**
     * 分解信息
     */
    private List<String> subMessage;
    /**
     * 分解出来的表达式
     */
    private List<String> spelExpression;
    /**
     * 参数集合
     */
    private Map<String, Object> parameter;
    /**
     * 穿透参数集合
     */
    private Map<String, Object> acrossParameter;
    /**
     * 解析结果
     */
    private String resultMessage;
    /**
     * 自定义方法
     */
    private Object rootObject;
    /**
     * 解析式结果
     */
    private Map<String, Object> analyticValue;
    /**
     * 表达式解析上下文
     */
    private StandardEvaluationContext spelCtx;
    /**
     * 条件
     */
    private String condition;
    /**
     * 是否能输出,使用此字段可以阻止输出
     */
    private boolean canOutput = true;
    /**
     * 扩展字段
     */
    private String extendAttribute;

}
