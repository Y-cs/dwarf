package org.dwarf.core.parse;


import org.dwarf.core.context.ParseContext;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 18:18
 * @Description:
 **/
public class LogParseByEmpty extends LogParse {
    @Override
    public void init() {

    }

    @Override
    public void doExecutor(ParseContext parseContext) {
        doNext(parseContext);
    }
}
