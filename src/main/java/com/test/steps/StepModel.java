package com.test.steps;

import com.test.global.ApiLoader;
import com.test.global.GlobalVariables;
import com.test.util.PlaceholderUtils;
import io.restassured.response.Response;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import static org.hamcrest.MatcherAssert.assertThat;

/**
 * @program: wetchat-service
 * @description: 用例中的step对象
 * @author: Mr.X
 * @create: 2021-02-28 16:03
 **/
public class StepModel {
    public static final Logger logger = LoggerFactory.getLogger(StepModel.class);

    /**
     * 1、需要定义AssertModel类
     */
    private String api;
    private String action;
    private ArrayList<String> actualParameter;
    private ArrayList<AssertModel> asserts;
    private HashMap<String, String> save;
    private HashMap<String, String> saveGlobal;

    /**
     * 2、需要定义StepResult类
     */
    private ArrayList<String> finalActualParaeter = new ArrayList<>();
    private HashMap<String, String> stepVariables = new HashMap<>();
    private StepResult stepResult = new StepResult();
    private ArrayList<Executable> assertList = new ArrayList<>();

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public ArrayList<String> getActualParameter() {
        return actualParameter;
    }

    public void setActualParameter(ArrayList<String> actualParameter) {
        this.actualParameter = actualParameter;
    }

    public ArrayList<AssertModel> getAsserts() {
        return asserts;
    }

    public void setAsserts(ArrayList<AssertModel> asserts) {
        this.asserts = asserts;
    }

    public HashMap<String, String> getSave() {
        return save;
    }

    public void setSave(HashMap<String, String> save) {
        this.save = save;
    }

    public HashMap<String, String> getSaveGlobal() {
        return saveGlobal;
    }

    public void setSaveGlobal(HashMap<String, String> saveGlobal) {
        this.saveGlobal = saveGlobal;
    }

    public StepResult run(HashMap<String, String> testCaseVariables){
        /**
         * 3、需要定义AssertModel类
         */
        if (actualParameter != null) {
            finalActualParaeter.addAll(PlaceholderUtils.resolveList(actualParameter, testCaseVariables));
        }
        /**
         * 4、根据case中配置的API对象和action信息，取出并执行相应的action
         */
        Response response = ApiLoader.getAction(api, action).run(finalActualParaeter);
        /**
         * 5、存储save
         */
        if (save != null) {
            save.forEach((variablesName, path)->{
                String value = response.path(path).toString();
                stepVariables.put(variablesName, value);
            });
        }
        /**
         * 6、存储saveGlobal
         */
        if (saveGlobal != null) {
            saveGlobal.forEach((variablesName, path)->{
                String value = response.path(path).toString();
                GlobalVariables.getGlobalVariables().put(variablesName, value);
            });
        }
        /**
         * 7、根据case中的配置对返回结果进行软断言，但不会终结测试将断言结果存入断言结果列表中，在用例最后进行统一结果输出
         */
        if (asserts != null) {
            asserts.forEach(assertModel -> {
               assertList.add(()->{

                   //利用反射获取需要断言的方式
                   Class<Matchers> matchersClazz = (Class<Matchers>) Class.forName("org.hamcrest.Matchers");
                   Method matherMethod = matchersClazz.getDeclaredMethod(assertModel.getMatcher(), Object.class);
                   Matcher invoke = (Matcher)matherMethod.invoke(matchersClazz.newInstance(), assertModel.getExpect());

                   //断言
                   assertThat(assertModel.getReason(),response.path(assertModel.getActual()).toString(),invoke);
               });
            });
        }
        /**
         * 8、将response和断言结果存储到stepResult对象中并返回
         */
        stepResult.setAssertList(assertList);
        stepResult.setStepVariables(stepVariables);
        stepResult.setResponse(response);

        return stepResult;
    }
}
