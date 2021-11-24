package org.dwarf.core.config;


import org.dwarf.core.context.PersistenceContext;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 14:38
 * @Description:
 **/
public interface RecordPersistence {

    /**
     * 保存
     *
     * @param persistenceContext
     */
    public void save(PersistenceContext persistenceContext);

}
