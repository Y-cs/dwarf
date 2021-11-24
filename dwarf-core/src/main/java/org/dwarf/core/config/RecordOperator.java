package org.dwarf.core.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 14:30
 * @Description: 操作人对象
 **/
@Data
@Accessors(fluent = true)
@AllArgsConstructor
@NoArgsConstructor
public class RecordOperator {

    private long id;

    private String name;


}
