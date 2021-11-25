package org.dwarf.core.parse;

import lombok.extern.slf4j.Slf4j;
import org.dwarf.core.context.ParseContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 13:28
 * @Description:
 **/
@Slf4j
public class LogParseBySpel extends LogParse {
    @Override
    public void init() {

    }

    @Override
    public void doExecutor(ParseContext parseContext) {
        StandardEvaluationContext ctx = new StandardEvaluationContext();
        parseContext.setSpelCtx(ctx);
        ctx.setRootObject(parseContext.getRootObject());
        if (parseContext.getAcrossParameter() != null) {
            ctx.setVariables(parseContext.getAcrossParameter());
        }
        if (parseContext.getParameter() != null) {
            ctx.setVariables(parseContext.getParameter());
        }
        //condition判断
        String condition = parseContext.getCondition();
        if (condition != null && parseContext.getCondition().length() > 0) {
            SpelExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression(condition.trim());
            Boolean conditionResult = exp.getValue(ctx, Boolean.class);
            if (Boolean.FALSE.equals(conditionResult)) {
                //条件判断未通过结束执行
                parseContext.setCanOutput(false);
                return;
            }
        }
        parseContext.setAnalyticValue(doAnalytic(parseContext.getSpelExpression(), ctx));
        super.doNext(parseContext);
    }

    private Map<String, Object> doAnalytic(List<String> list, StandardEvaluationContext ctx) {
        Map<String, Object> value = new HashMap<>(list == null ? 0 : list.size());
        if (list != null && list.size() > 0) {
            for (String spel : list) {
                value.put(spel, this.doParseSpel(spel, ctx));
            }
        }
        return value;
    }

    private Object doParseSpel(String spel, StandardEvaluationContext ctx) {
        try {
            SpelExpressionParser parser = new SpelExpressionParser();
            Expression exp = parser.parseExpression(spel);
            return exp.getValue(ctx);
        } catch (Exception e) {
            if (log.isDebugEnabled()) {
                log.error("Spel解析错误:", e);
            }
            return null;
        }
    }

}
