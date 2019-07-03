package com.stj.common.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Package: com.bxstar.common.utils
 * @Description: ResponseUtils
 * @author: yilan.hu
 * @data: 2019/5/30
 */
public class ResponseUtils {

    public static void fallback(HttpServletResponse response, Object o) {
        if (response == null) {
            return;
        }
        String result = JSONObject.toJSONString(o);
        PrintWriter writer = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            writer = response.getWriter();
            writer.write(result);
        } catch (IOException e) {
            if (writer != null) {
                writer.close();
            }
        }
    }
}
