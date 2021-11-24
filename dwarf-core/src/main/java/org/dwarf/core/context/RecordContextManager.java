package org.dwarf.core.context;

import java.util.*;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 15:01
 * @Description:
 **/
public enum RecordContextManager {
    /**
     * 单例
     */
    INSTANCE;

    public ThreadLocal<RecordContext> contextThreadLocal = new ThreadLocal<>();

    /**
     * 获取上下文
     *
     * @return 日志上下文
     */
    public RecordContext getContext() {
        RecordContext recordContext = contextThreadLocal.get();
        if (recordContext == null) {
            recordContext = new RecordContext();
            contextThreadLocal.set(recordContext);
        }
        return recordContext;
    }

    /**
     * 添加上下文对象
     *
     * @param parameterName 参数名
     * @param parameter     参数
     */
    public void addParam(String parameterName, Object parameter) {
        addParam(parameterName, parameter, false);
    }

    /**
     * 添加上下文对象
     *
     * @param parameterName 参数名
     * @param parameter     参数
     * @param isAcross      是否可以穿透方法界限
     */
    public void addParam(String parameterName, Object parameter, boolean isAcross) {
        RecordContext context = getContext();
        if (isAcross) {
            Map<String, Object> acrossParameter = Optional.ofNullable(context.getAcrossParameter()).orElse(new HashMap<>());
            acrossParameter.put(parameterName, parameter);
            context.setAcrossParameter(acrossParameter);
        } else {
            List<Map<String, Object>> parameterMaps = Optional.ofNullable(context.getParameter()).orElse(new ArrayList<>());
            /*
                这里所有的流程都是在一个线程里进行的
                所以一切都是顺序的 所以顺序应该是
                1->2->3->2->1
                不会出现跨步的情况
             */
            int index = context.getStep() - 1;
            Map<String, Object> parameterMap;
            if (parameterMaps.size() > index) {
                parameterMap = Optional.ofNullable(parameterMaps.get(index)).orElse(new HashMap<>());
                parameterMaps.set(index, parameterMap);
            } else {
                parameterMap = new HashMap<>();
                parameterMaps.add(index, parameterMap);
            }
            parameterMap.put(parameterName, parameter);
            context.setParameter(parameterMaps);
        }
    }

    public void clear() {
        contextThreadLocal.remove();
    }

}
