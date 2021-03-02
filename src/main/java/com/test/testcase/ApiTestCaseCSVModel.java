package com.test.testcase;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.test.steps.StepModel;
import com.test.steps.StepModelCSV;
import com.test.steps.StepResult;
import com.test.util.CSVParam;
import com.test.util.FakerUtils;
import org.junit.jupiter.api.function.Executable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertAll;

/**
 * @program: wetchat-service
 * @description: 用例yaml对应的数据对象
 * @author: Mr.X
 * @create: 2021-02-28 20:56
 **/
public class ApiTestCaseCSVModel {
    public static final Logger logger = LoggerFactory.getLogger(ApiTestCaseCSVModel.class);

    private String name;
    private String description;
    private ArrayList<StepModelCSV> steps;
    private ArrayList<Executable> assertList = new ArrayList<>();
    private HashMap<String, String> testCaseVariables = new HashMap<>();
    private String csvParameter;

    private String[][] csvFileData;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<StepModelCSV> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<StepModelCSV> steps) {
        this.steps = steps;
    }

    public ArrayList<Executable> getAssertList() {
        return assertList;
    }

    public void setAssertList(ArrayList<Executable> assertList) {
        this.assertList = assertList;
    }

    public HashMap<String, String> getTestCaseVariables() {
        return testCaseVariables;
    }

    public void setTestCaseVariables(HashMap<String, String> testCaseVariables) {
        this.testCaseVariables = testCaseVariables;
    }

    public String getCsvParameter() {
        return csvParameter;
    }

    public void setCsvParameter(String csvParameter) {
        this.csvParameter = csvParameter;
    }

    public static ApiTestCaseCSVModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path), ApiTestCaseCSVModel.class);
    }

    public void run() throws Exception {
        /**
         * 1、加载用例层关键字变量
         */
        this.testCaseVariables.put("getTimeStamp", FakerUtils.getTimeStamp());
        logger.info("用例变量更新： "+testCaseVariables);
        //处理SVC文件数据
        if (csvParameter != null) {
            // 获取到csv中文件数据
            csvFileData = CSVParam.readCSVFileData(csvParameter);
        }
        /**
         * 2、遍历step进行执行
         */
         steps.forEach(step->{
             ArrayList<String> actualParameter = step.getActualParameter();
             if (actualParameter != null && actualParameter.size()>0 && actualParameter.toString().contains("${csv")){
                 // 遍历csv数据 并将数据添加到用例变量中
                 // 数据组数决定了需要运行的次数
                 Arrays.stream(csvFileData).forEach(data->{
                     for (int i = 0; i < data.length; i++) {
                         // 将数据以 ${csv0} : data 的形式存入
                         testCaseVariables.put("csv" + i, data[i]);
                     }
                     StepResult stepResult = step.run(testCaseVariables);
                     // 3、处理结果变量
                     if (stepResult.getStepVariables() != null && stepResult.getStepVariables().size() > 0) {
                         testCaseVariables.putAll(stepResult.getStepVariables());
                         logger.info("更新用例变量 新增：" + stepResult.getStepVariables());
                     }
                     /**
                      * 4、处理assertList,并进行统一断言
                      * */
                     if (stepResult.getAssertList().size()>0) {
                         assertList.addAll(stepResult.getAssertList());
                     }
                 });
             }else {
                 StepResult stepResult = step.run(testCaseVariables);
                 /**
                  * 3、处理step返回的save变量
                  */
                 if (stepResult.getStepVariables().size() > 0) {
                     testCaseVariables.putAll(stepResult.getStepVariables());
                 }
                 /**
                  * 4、处理assertList,并进行统一断言
                  */
                 if (stepResult.getAssertList().size()>0) {
                     assertList.addAll(stepResult.getAssertList());
                 }
             }
         });

        /**
         * 5、进行统一断言
         */
        assertAll("",assertList.stream());
    }
}
