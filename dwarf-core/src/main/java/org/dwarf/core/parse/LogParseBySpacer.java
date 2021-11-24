package org.dwarf.core.parse;


import org.dwarf.core.context.ParseContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 10:20
 * @Description:
 **/
public class LogParseBySpacer extends LogParse {
    @Override
    public void init() {

    }

    @Override
    public void doExecutor(ParseContext parseContext) {
        this.handleSpacer(parseContext);
        super.doNext(parseContext);
    }

    /**
     * 拆解整个信息
     *
     * @param parseContext
     */
    protected void handleSpacer(ParseContext parseContext) {
        String metaMessage = parseContext.getMetaMessage();
        List<String> message = new ArrayList<>();
        List<String> expression = new ArrayList<>();
        int indexTemp = -1, indexStart = -1, startTemp = 0, endTemp = 0;
        while (indexStart < metaMessage.length()) {
            indexTemp = indexStart + 1;
            if ((indexStart = startTemp = metaMessage.indexOf(getConfig().getSplit().getStartChar(), indexStart + 1)) == -1) {
                break;
            }
            if ((indexStart = endTemp = metaMessage.indexOf(getConfig().getSplit().getEndChar(), indexStart + 1)) == -1) {
                break;
            }
            message.add(metaMessage.substring(indexTemp, startTemp));
            expression.add(this.handleSpel(metaMessage.substring(startTemp, endTemp + 1)));
        }
        message.add(metaMessage.substring(indexTemp));
        parseContext.setSubMessage(message);
        parseContext.setSpelExpression(expression);
    }

    private String handleSpel(String spel) {
        return spel.substring(getConfig().getSplit().getStartChar().length(),
                        spel.length() - getConfig().getSplit().getEndChar().length())
                .trim();
    }


}
