package com.test.steps;

import io.restassured.response.Response;

/**
 * @program: wetchat-service
 * @description: 用来存储断言数据的对象
 * @author: Mr.X
 * @create: 2021-02-28 16:05
 **/
public class AssertModel {
    private String actual;
    private String matcher;
    private String expect;
    private String reason;

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }

    public String getMatcher() {
        return matcher;
    }

    public void setMatcher(String matcher) {
        this.matcher = matcher;
    }

    public String getExpect() {
        return expect;
    }

    public void setExpect(String expect) {
        this.expect = expect;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
    public void run(Response response){

    }

}
