package org.dwarf.spring.controller;

import org.dwarf.spring.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: YuanChangShuai
 * @Date: 2021/11/23 19:07
 * @Description:
 **/
@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping("test")
    public void test() {
        long l = System.currentTimeMillis();
        testService.test("这是信息");
        System.out.println(System.currentTimeMillis() - l);
    }

}
