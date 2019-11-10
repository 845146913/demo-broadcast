package com.example.demo.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JsonMapper {

    private final static ObjectMapper m = new ObjectMapper();

    public static <T> T jsonToObj(String json, Class<T> clazz) {
        T o = null;
        try {
            o = m.readValue(json, clazz);
        } catch (Exception e) {
            log.error("\r\n >>>>>>>>> CONVERT OBJECT　FAILED! e:{}", e.getMessage());
        }
        return o;
    }

    public static String objToJson(Object o) {
        try {
            return m.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            log.error("\r\n >>>>>>>>> CONVERT STRING　FAILED! e:{}", e);
            return null;
        }
    }
}