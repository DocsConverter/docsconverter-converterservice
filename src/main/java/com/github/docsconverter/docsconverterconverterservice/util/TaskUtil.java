package com.github.docsconverter.docsconverterconverterservice.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.docsconverter.docsconverterconverterservice.to.Task;

public class TaskUtil {
    public static String serializeToJSON(Task task){
        try {
            return new ObjectMapper().writeValueAsString(task);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }

    public static Task deserializeToObject(String json){
        try {
            return new ObjectMapper().readValue(json, Task.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException();
        }
    }

    public static String getName(String url){
        String[] split = url.split("/");
        return split[split.length-1];
    }
}
