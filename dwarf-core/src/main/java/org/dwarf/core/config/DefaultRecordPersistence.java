package org.dwarf.core.config;

import lombok.extern.slf4j.Slf4j;
import org.dwarf.core.context.PersistenceContext;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 15:19
 * @Description:
 **/
@Slf4j
public class DefaultRecordPersistence implements RecordPersistence {
    @Override
    public void save(PersistenceContext persistenceContext) {
        log.info(persistenceContext.toString());
    }
}
