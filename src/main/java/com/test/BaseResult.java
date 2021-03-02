package com.test;

import io.restassured.response.Response;

/**
 * @program: apiframework
 * @description:用于保存结果的对象基类
 * @author: Mr.X
 * @create: 2021-02-08 14:41
 **/
public class BaseResult {
    private Response response;

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }
}
