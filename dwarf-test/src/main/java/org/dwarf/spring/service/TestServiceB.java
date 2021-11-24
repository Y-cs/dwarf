package org.dwarf.spring.service;

import org.dwarf.core.ano.LogParam;
import org.dwarf.core.ano.Record;
import org.springframework.stereotype.Service;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 19:12
 * @Description:
 **/
@Service
public class TestServiceB {

    @Record(success = "s:`#s`,integer:`#in`")
    public void testB(@LogParam(isAcross = true) Integer in) {

    }


}
