package org.dwarf.core.factory;


import org.dwarf.core.config.RecordConfig;
import org.dwarf.core.context.ParseContext;
import org.dwarf.core.parse.*;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 18:09
 * @Description: 解析功能责任链工厂
 **/
public class RecordParseFactory {

    private final RecordConfig recordConfig;

    private final LogParse head = new LogParseByEmpty();

    private volatile LogParse point = head;

    public RecordParseFactory(RecordConfig recordConfig) {
        this.recordConfig = recordConfig;

        this.addParse(new LogParseBySpacer());
        this.addParse(new LogParseBySpel());
        this.addParse(new LogParseByAssemble());
    }

    public LogParse getParse() {
        return head;
    }

    /**
     * 添加解析
     *
     * @param logParse 解析器
     */
    public void addParse(LogParse logParse) {
        logParse.setConfig(this.recordConfig);
        logParse.init();
        this.point.setNext(logParse);
        this.point = logParse;
    }

    /**
     * 执行
     *
     * @param parseContext 上下文
     */
    public void doExecutor(ParseContext parseContext) {
        this.head.doExecutor(parseContext);
    }


}
