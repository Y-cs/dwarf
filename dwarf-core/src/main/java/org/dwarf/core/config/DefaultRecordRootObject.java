package org.dwarf.core.config;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 14:28
 * @Description:
 **/
public class DefaultRecordRootObject implements RecordRootObject {
    @Override
    public RecordOperator operator() {
        return new RecordOperator();
    }
}
