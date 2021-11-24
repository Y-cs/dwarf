package org.dwarf.core.parse;


import org.dwarf.core.context.ParseContext;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 13:39
 * @Description:
 **/
public class LogParseByAssemble extends LogParse {
    @Override
    public void init() {

    }

    @Override
    public void doExecutor(ParseContext parseContext) {
        setPullOffMessage(parseContext);
        super.doNext(parseContext);
    }

    private void setPullOffMessage(ParseContext parseContext) {
        List<String> messages = parseContext.getSubMessage();
        List<String> expression = parseContext.getSpelExpression();
        Iterator<String> expressionIterator = expression.iterator();
        Map<String, Object> values = parseContext.getAnalyticValue();
        StringBuilder pullOffMsg = new StringBuilder();
        for (String message : messages) {
            pullOffMsg.append(message);
            if (expressionIterator.hasNext()) {
                pullOffMsg.append(values.get(expressionIterator.next()));
            }
        }
        parseContext.setResultMessage(pullOffMsg.toString());
    }
}
