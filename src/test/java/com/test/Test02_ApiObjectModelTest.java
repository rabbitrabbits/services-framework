/**
 * projectName: apiobject-framwork
 * fileName: Test01_ApiActionModelTest.java
 * packageName: com.apiobject.test
 * date: 2020-12-26 下午2:17
 */
package com.test;


import com.test.api.ApiObjectModel;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @version: V1.0
 * @author: kuohai
 * @className: Test01_ApiActionModelTest
 * @packageName: com.apiobject.test
 * @description: 对ApiAction对象实现的单元测试
 * @data: 2020-12-26 下午2:17
 **/
public class Test02_ApiObjectModelTest {
    public static final Logger logger = LoggerFactory.getLogger(Test02_ApiObjectModelTest.class);

    @Test
    void loadTest() throws IOException {
        ArrayList<String> actualParameter = new ArrayList<>();
        actualParameter.add("ww32da6dddc8a631e9");
        actualParameter.add("Tc5o53zJOLdtYGSz8UbIOAb8oqs0AIluZu1dY5dXi30");

        ApiObjectModel apiObjectModel= ApiObjectModel.load("src/test/resources/api/tokenhelper.yaml");
        apiObjectModel.getActions().get("getToken").run(actualParameter);
    }

}