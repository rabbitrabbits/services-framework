package com.test.steps;

import com.test.BaseResult;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @program: wetchat-service
 * @description: step执行结果对象
 * @author: Mr.X
 * @create: 2021-02-28 16:13
 **/
public class StepResult extends BaseResult {
    private ArrayList<Executable> assertList;
    private HashMap<String, String> stepVariables = new HashMap<>();

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getStepVariables() {
        return stepVariables;
    }

    public void setStepVariables(HashMap<String, String> stepVariables) {
        this.stepVariables = stepVariables;
    }

}
