package com.test;

import com.test.global.ApiLoader;
import com.test.testcase.ApiTestCaseModel;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

/**
 * @program: wetchat-service
 * @description: 部门测试用例
 * @author: Mr.X
 * @create: 2021-02-28 21:55
 **/
public class DepartMentTest {

    @BeforeAll
    static void loadTest() throws IOException {
        ApiLoader.load("src/test/resources/api");
        ApiTestCaseModel.load("src/test/resources/setup/gettoken.yaml").run();
    }

    @Test
    void CURDTest() throws IOException {
        ApiTestCaseModel.load("src/test/resources/depart/departmentcrud.yaml").run();
    }
}
