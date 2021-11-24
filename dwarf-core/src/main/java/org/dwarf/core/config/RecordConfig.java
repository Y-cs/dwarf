package org.dwarf.core.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 15:21
 * @Description:
 **/
@Getter
@Setter
public class RecordConfig {

    private SplitConfig split = new SplitConfig();

    private RecordRootObject recordRootObject = new DefaultRecordRootObject();

    private RecordPersistence persistence = new DefaultRecordPersistence();

}
