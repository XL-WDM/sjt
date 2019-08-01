package com.sjt.common.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * @author: yilan.hu
 * @data: 2019/8/1
 */
public class JsonUtils {

    /**
     * 格式化成JSON报文
     * @param o
     * @return
     */
    public static String toJson(Object o) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();
        Gson gson = gsonBuilder.create();

        return gson.toJson(o);
    }
}
