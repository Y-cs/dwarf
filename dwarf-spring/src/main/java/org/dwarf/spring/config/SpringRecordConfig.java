package org.dwarf.spring.config;

import org.dwarf.core.config.RecordConfig;
import org.dwarf.core.config.RecordPersistence;
import org.dwarf.core.config.RecordRootObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 16:21
 * @Description:
 **/
@ConfigurationProperties(prefix = "record")
public class SpringRecordConfig extends RecordConfig {

    public SpringRecordConfig(RecordRootObject recordRootObject, RecordPersistence persistence) {
        setRecordRootObject(recordRootObject);
        setPersistence(persistence);
    }

}
