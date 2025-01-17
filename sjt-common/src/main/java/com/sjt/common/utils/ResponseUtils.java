package com.sjt.common.utils;

import com.alibaba.fastjson.JSONObject;
import com.sjt.common.base.constant.BaseConstant;

import javax.servlet.http.Cookie;
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

    public static void setCookie(HttpServletResponse response, String name,
                                 String value, String path, int maxAge) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath(path);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static void setCookie(HttpServletResponse response, String name,
                                 String value, int maxAge) {
        setCookie(response, name, value, BaseConstant.Character.SLASH, maxAge);
    }

    public static void setCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, BaseConstant.Character.SLASH, Integer.MAX_VALUE);
    }
}
