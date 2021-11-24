package org.dwarf.core.context;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/22 15:10
 * @Description:
 **/
@Data
public class RecordContext {

    /**
     * 重入次数
     */
    private int step;
    /**
     * 参数
     */
    private List<Map<String, Object>> parameter;

    /**
     * 通用参数
     */
    private Map<String, Object> acrossParameter;

    /**
     * 进
     */
    public void stepForward() {
        this.step++;
    }

    /**
     * 退
     */
    public void stepBack() {
        this.step--;
    }
}
