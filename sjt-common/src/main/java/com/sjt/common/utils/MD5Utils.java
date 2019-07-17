package com.sjt.common.utils;

import org.springframework.util.DigestUtils;

/**
 * @author: yilan.hu
 * @data: 2019/5/24
 */
public class MD5Utils {
    /**
     * 混淆加密
     * @param text
     * @param slot
     * @return
     */
    public static String getMD5(String text, String slot) {
        String base = text + "/" + slot;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
