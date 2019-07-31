package com.sjt.common.utils;

import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.base.constant.CharsetConstant;
import org.springframework.util.DigestUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author: yilan.hu
 * @data: 2019/5/24
 */
public class MD5Utils {

    /**
     * 加密
     * @param text
     * @return
     */
    public static String getMD5(String text) {
        return getMD5(text, "");
    }

    /**
     * 混淆加密
     * @param text
     * @param slot
     * @return
     */
    public static String getMD5(String text, String slot) {
        String data = text + slot;
        String md5 = DigestUtils.md5DigestAsHex(data.getBytes());
        return md5.toUpperCase();
    }
}
