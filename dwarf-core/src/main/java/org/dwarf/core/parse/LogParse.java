package org.dwarf.core.parse;


import org.dwarf.core.config.RecordConfig;
import org.dwarf.core.context.ParseContext;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 18:15
 * @Description:
 **/
public abstract class LogParse {

    private RecordConfig config;

    private LogParse next;

    /**
     * 初始化
     */
    public abstract void init();

    /**
     * 执行逻辑
     *
     * @param parseContext 处理上下文
     */
    public abstract void doExecutor(ParseContext parseContext);

    public void setConfig(RecordConfig config) {
        this.config = config;
    }

    public void setNext(LogParse next) {
        this.next = next;
    }

    protected RecordConfig getConfig() {
        return config;
    }

    protected void doNext(ParseContext parseContext) {
        if (next != null) {
            next.doExecutor(parseContext);
        }
    }
}
