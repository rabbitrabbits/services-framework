package com.test.global;

import com.test.actions.ApiActionModel;
import com.test.api.ApiObjectModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program: wetchat-service
 * @description: 接口对象加载器
 * @author: Mr.X
 * @create: 2021-02-28 15:49
 **/
public class ApiLoader {
    public static final Logger logger = LoggerFactory.getLogger(ApiLoader.class);

    /**
     * 加载所有api Object对象，并保存到本列表中
     */
    private static List<ApiObjectModel> apis = new ArrayList<>();

    public static void load(String dir){
        Arrays.stream(new File(dir).list()).forEach(path->{
            try {
                apis.add(ApiObjectModel.load(dir+File.separator+path));
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        });
    }

    public static ApiActionModel getAction(String apiName, String actionName){
        final ApiActionModel[] apiActionModel = {new ApiActionModel()};
        apis.stream().filter(api->
           api.getName().equals(apiName)
        ).forEach(api->{
            apiActionModel[0] = api.getActions().get(actionName);
        });
        if (apiActionModel[0] != null) {
            return apiActionModel[0];
        } else {
            logger.error("没有找到接口对象：" + apiName + "中的action：" + actionName);
        }
        return null;
    }
}
