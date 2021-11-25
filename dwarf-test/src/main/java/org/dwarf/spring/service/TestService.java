package org.dwarf.spring.service;

import org.dwarf.core.ano.LogParam;
import org.dwarf.core.ano.Record;
import org.dwarf.core.context.RecordContextManager;
import org.springframework.stereotype.Service;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 19:08
 * @Description:
 **/
@Service
public class TestService {

    public final TestServiceB testServiceB;


    public TestService(TestServiceB testServiceB) {
        this.testServiceB = testServiceB;
    }

    @Record(success = "chenggong:`#s`,in:`#in`", condition = "#i!=10")
    public boolean test(@LogParam(name = "s", isAcross = true) String ss) {
        testServiceB.testB(12);
        RecordContextManager.INSTANCE.addParam("i", 10);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

}
