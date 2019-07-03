package com.stj.common.utils;

import org.springframework.util.DigestUtils;

/**
 * @author: yilan.hu
 * @data: 2019/5/24
 */
public class MD5Utils {

    /**
     * 用于混淆md5
     */
    private final static String SLAT = "q2oy43#2je$r&khf(*3A?>sd!213!@3~sfd*(#75^43";

    public static String getMD5(String text) {
        String base = text + "/" + SLAT;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
