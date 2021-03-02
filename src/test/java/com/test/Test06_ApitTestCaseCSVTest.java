package com.test;

import com.test.global.ApiLoader;
import com.test.testcase.ApiTestCaseCSVModel;
import com.test.testcase.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @program: wetchat-service
 * @description:csv文件读取测试
 * @author: Mr.X
 * @create: 2021-03-01 17:36
 **/
public class Test06_ApitTestCaseCSVTest {
    @BeforeAll
    static void loadTest() throws IOException {
        ApiLoader.load("src/test/resources/api");
        ApiTestCaseModel.load("src/test/resources/setup/gettoken.yaml").run();
    }

    @Test
    void  runTest() throws Exception {
        ApiTestCaseCSVModel apiTestCaseCSVModel = ApiTestCaseCSVModel.load("src/test/resources/depart/creatdepartment_csv.yaml");
        apiTestCaseCSVModel.run();
    }
}
