package org.dwarf.spring.service;

import org.dwarf.core.ano.RecordParam;
import org.dwarf.core.ano.Record;
import org.springframework.stereotype.Service;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 19:12
 * @Description:
 **/
@Service
public class TestServiceB {

    @Record(success = "s:`#s`,integer:`#in`",condition = "#in!=12")
    public void testB(@RecordParam(isAcross = true) Integer in) {

    }


}
