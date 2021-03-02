package com.test.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.test.actions.ApiActionModel;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

/**
 * @program: wetchat-service
 * @description: api对象类
 * @author: Mr.X
 * @create: 2021-02-28 15:25
 **/
public class ApiObjectModel {
    private String name;
    private HashMap<String, ApiActionModel> actions;
    private HashMap<String, String> obVariables = new HashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ApiActionModel> getActions() {
        return actions;
    }

    public void setActions(HashMap<String, ApiActionModel> actions) {
        this.actions = actions;
    }

    public HashMap<String, String> getObVariables() {
        return obVariables;
    }

    public void setObVariables(HashMap<String, String> obVariables) {
        this.obVariables = obVariables;
    }

    public static ApiObjectModel load(String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        return objectMapper.readValue(new File(path), ApiObjectModel.class);
    }
}
