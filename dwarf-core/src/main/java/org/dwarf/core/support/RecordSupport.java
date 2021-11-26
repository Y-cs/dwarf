package org.dwarf.core.support;

import org.aspectj.lang.reflect.MethodSignature;
import org.dwarf.core.ano.RecordParam;
import org.dwarf.core.ano.Record;
import org.dwarf.core.config.ParameterKey;
import org.dwarf.core.config.RecordConfig;
import org.dwarf.core.config.RecordRootObject;
import org.dwarf.core.context.ParseContext;
import org.dwarf.core.context.PersistenceContext;
import org.dwarf.core.context.RecordContext;
import org.dwarf.core.context.RecordContextManager;
import org.dwarf.core.enums.ActiveCycleEnum;
import org.dwarf.core.parse.LogParse;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 15:42
 * @Description: 每个方法持有一个 操作上下文的前进和后退
 **/
public class RecordSupport {

    /**
     * 配置文件
     */
    private final RecordConfig recordConfig;

    /**
     * 解析责任链
     */
    private final LogParse logParse;

    /**
     * 日志对象
     */
    private Record record;

    /**
     * 错误
     */
    private Throwable throwable;

    /**
     * 生命周期
     */
    private ActiveCycleEnum cycle = ActiveCycleEnum.NOT_RUN;

    public RecordSupport(RecordConfig recordConfig, LogParse logParse) {
        this.recordConfig = recordConfig;
        this.logParse = logParse;
        //步进
        RecordContextManager.INSTANCE.getContext().stepForward();
    }

    /**
     * 该资源务必清理 不清理会导致 后续线程无法正常使用
     */
    public void clear() {
        //后退
        RecordContext context = RecordContextManager.INSTANCE.getContext();
        context.stepBack();
        //退到起点清理数据
        if (context.getStep() == 0) {
            RecordContextManager.INSTANCE.clear();
        }
    }

    /**
     * 初始化方法和参数
     *
     * @param methodSignature
     * @param args
     */
    public void initMethodAndArgs(MethodSignature methodSignature, List<Object> args) {
        this.changeCycle(ActiveCycleEnum.IS_RUN);
        //方法
        Method method = methodSignature.getMethod();
        RecordContextManager.INSTANCE.addParam(ParameterKey.CLASS.getKey(), methodSignature.getDeclaringType());
        RecordContextManager.INSTANCE.addParam(ParameterKey.METHOD.getKey(), method);
        //形参名
        String[] parameterNames = methodSignature.getParameterNames();
        //形参对象
        Annotation[][] parameterAnnotations = method.getParameterAnnotations();
        for (int i = 0; i < parameterAnnotations.length; i++) {
            //参数名
            String name = parameterNames[i];
            //参数注解
            RecordParam recordParam = Arrays.stream(parameterAnnotations[i])
                    .filter(pa -> pa.annotationType().equals(RecordParam.class))
                    .map(pa -> (RecordParam) pa).findFirst().orElse(null);
            if (recordParam != null) {
                if (recordParam.name() != null && recordParam.name().length() > 0) {
                    //有别名
                    name = recordParam.name();
                }
            }
            if (recordParam != null && recordParam.isAcross()) {
                //穿透参数
                RecordContextManager.INSTANCE.addParam(name, args.get(i), true);
            } else {
                RecordContextManager.INSTANCE.addParam(name, args.get(i));
            }
        }
    }

    /**
     * 设置结果参数
     *
     * @param result
     */
    public void setResult(Object result) {
        this.changeCycle(ActiveCycleEnum.NORMAL_END);
        RecordContextManager.INSTANCE.addParam(ParameterKey.RESULT.getKey(), result);
    }

    /**
     * 设置错误
     *
     * @param throwable
     */
    public void setException(Throwable throwable) {
        //表示此方法运行中已经发生错误
        this.changeCycle(ActiveCycleEnum.EXCEPTION);
        this.throwable = throwable;
        RecordContextManager.INSTANCE.addParam(ParameterKey.EXCEPTION.getKey(), throwable);
    }

    /**
     * 持久化
     */
    public void persistence() {
        //获取上下文
        RecordContext context = RecordContextManager.INSTANCE.getContext();
        //解析上下文
        ParseContext parseContext = new ParseContext();
        //持久化上下文
        PersistenceContext persistenceContext = new PersistenceContext();
        //持久化参数
        Map<String, Object> persistenceParameter = new HashMap<>(16);
        //处理参数集合
        if (context.getAcrossParameter() != null) {
            parseContext.setAcrossParameter(context.getAcrossParameter());
            persistenceParameter.putAll(context.getAcrossParameter());
        }
        List<Map<String, Object>> parameters = context.getParameter();
        if (parameters != null && parameters.size() >= context.getStep()) {
            Map<String, Object> parameter = parameters.get(context.getStep() - 1);
            if (parameter != null) {
                parseContext.setParameter(parameter);
                persistenceParameter.putAll(parameter);
            }
        }
        persistenceContext.setParameter(persistenceParameter);
        //spel root object
        RecordRootObject recordRootObject = this.recordConfig.getRecordRootObject();
        parseContext.setRootObject(recordRootObject);
        parseContext.setCondition(this.record.condition());
        parseContext.setExtendAttribute(this.record.extendAttribute());
        switch (cycle) {
            case NOT_RUN:
            case IS_RUN:
            case NORMAL_END:
                //成功结果
                parseContext.setMetaMessage(this.record.success());
                persistenceContext.setSuccess(true);
                break;
            case EXCEPTION:
                //失败结果
                parseContext.setMetaMessage(this.record.fail());
                persistenceContext.setSuccess(false);
                break;
            default:
        }
        //执行解析
        this.logParse.doExecutor(parseContext);
        if (parseContext.isCanOutput()) {
            //解析结果
            persistenceContext.setMessage(parseContext.getResultMessage());
            //操作人
            persistenceContext.setOperator(recordRootObject.operator());
            //报错内容
            persistenceContext.setThrowable(this.throwable);
            persistenceContext.setBusinessCode(this.record.businessCode());
            persistenceContext.setExtend(parseContext.getExtendAttribute());
            this.recordConfig.getPersistence().save(persistenceContext);
        }
    }

    /**
     * 设置参数对象
     *
     * @param record
     */
    public void setRecord(Record record) {
        this.record = record;
    }

    /**
     * 修改运行周期
     *
     * @param cycle
     */
    private void changeCycle(ActiveCycleEnum cycle) {
        if (this.cycle == null || cycle.ordinal() > this.cycle.ordinal()) {
            //修改状态的index大于当前状态允许修改
            this.cycle = cycle;
        }
    }

}
