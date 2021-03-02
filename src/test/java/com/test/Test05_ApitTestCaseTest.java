package com.test;

import com.test.global.ApiLoader;
import com.test.testcase.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @program: wetchat-service
 * @description: 对ApiAction对象实现的单元测试
 * @author: Mr.X
 * @create: 2021-02-28 21:34
 **/
public class Test05_ApitTestCaseTest {
    public static final Logger logger = LoggerFactory.getLogger(Test05_ApitTestCaseTest.class);

    @BeforeAll
    static void loadTest(){
        ApiLoader.load("src/test/resources/api");
        logger.info("debugger!");
    }

    @Test
    void  runTest() throws IOException {
        ApiTestCaseModel apiTestCaseModel = ApiTestCaseModel.load("src/test/resources/testcase/creatdepartment.yaml");
        apiTestCaseModel.run();
    }
}
