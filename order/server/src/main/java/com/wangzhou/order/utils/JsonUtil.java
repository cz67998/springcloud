package com.wangzhou.order.utils;


import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


/**
 * Created by IDEA
 * author:wangzhou
 * Data:2018/10/22
 * Time:11:51
 **/
@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String toJson(Object object) {
        try{
          return objectMapper.writeValueAsString(object)  ;
        }catch(JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object fromJson(String string,Class classType){
        try{
            return objectMapper.readValue(string,classType)  ;
        }catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param string
     * @param typeReference
     * @return
     */
    public static Object fromJsonList(String string, TypeReference typeReference){
        try{
            return objectMapper.readValue(string,typeReference)  ;
        }catch(IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
