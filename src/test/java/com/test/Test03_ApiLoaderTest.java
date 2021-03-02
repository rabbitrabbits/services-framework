package com.test;


import com.test.global.ApiLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

/**
 * @program: apiframework
 * @description: 对ApiAction对象实现的单元测试
 * @author: Mr.X
 * @create: 2021-02-09 10:51
 **/
public class Test03_ApiLoaderTest {
    public static final Logger logger = LoggerFactory.getLogger(Test03_ApiLoaderTest.class);

    @BeforeAll
    static void loadTest(){
        ApiLoader.load("src/test/resources/api");
        logger.info("debugger");
    }

    @Test
    void getActionTest() {
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("ww32da6dddc8a631e9");
        actualParameter.add("Tc5o53zJOLdtYGSz8UbIOAb8oqs0AIluZu1dY5dXi30");
        ApiLoader.getAction("tokenhelper", "getToken").run(actualParameter);
    }
}
