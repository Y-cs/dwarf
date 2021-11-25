# 操作日志记录组件

### 现已完成功能

1. 支持日志方法上的重入
2. 支持日志参数的穿透
3. 完成SpringAop
4. 支持Spel的参数解析
5. 支持自定义方法扩展
6. 自定义日志持久化
7. Condition条件判断

### 使用方法

> @Record(success = "XXX已完成,参数:\`Spel解析式\`",fail="XXX已完成,参数:\`Spel解析式\`",businessCode="XXX服务")

##### Spel的解析式

Spel的解析式可以解析的参数是方法的入参和自定义参数:

1. 方法的入参
    1. 默认的解析名是参数名,也可以使用@LogParam(name="")来指定名称
2. 流程中自定义的参数
    1. 使用`RecordContextManager.INSTANCE.addParam(key,value);`来指定名称

##### 参数的穿透

参数的穿透是指,当A方法调用B方法B方法的日志希望使用A方法中的参数,或者B方法的日志希望使用A方法的参数

1. 方法的入参
    1. 使用`@LogParam(isAcross=true)`即可穿透使用
2. 流程中自定义的参数
    1. 使用`RecordContextManager.INSTANCE.addParam(key,value,true);`即可穿透使用

##### 重入问题解决

```
当发生A方法调用B方法时,A方法和B方法都存在该日志注解
再次createRecord对象时会从当前线程中获取到日志操作上下文
step前进一步,通过List<Map>隔离参数,同时实现参数穿透的方案
然后在执行结束后调用RecordSupport.clear使step后退一步
当退到0时清理线程中的上下文,以此来解决重入
```

##### 日志的持久化

持久化日志是使用者自定义的方案,需实现`RecordPersistence`接口<br>
可以使用spring容器

##### 自定义方法

例如获取操作人对象,需实现`RecordRootObject`接口 可以使用spring容器

##### Spring的支持

需在配置类上注解`@EnableRecord`

##### 性能消耗

根据统计刨除IO操作,耗时在1-3ms之间,基本无影响

##### Condition条件判断

解析前会优先解析condition,如果condition判断没有通过会阻止后续流程