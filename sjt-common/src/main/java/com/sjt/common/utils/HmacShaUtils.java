package com.sjt.common.utils;

import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.base.constant.CharsetConstant;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author: yilan.hu
 * @data: 2019/7/31
 */
public class HmacShaUtils {

    private static final String HMACSHA256 = "HmacSHA256";


    public static String hmacSha256(String data, String key) throws Exception {
        if (StringUtils.isEmpty(data) || StringUtils.isEmpty(key)) {
            return "";
        }

        Mac sha256Hmac = Mac.getInstance(HMACSHA256);

        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes(CharsetConstant.CHAR_UTF_8), HMACSHA256);

        sha256Hmac.init(secretKey);

        byte[] array = sha256Hmac.doFinal(data.getBytes(CharsetConstant.CHAR_UTF_8));

        StringBuilder builder = new StringBuilder();

        for (byte item : array) {
            builder.append(Integer.toHexString((item & 0xFF) | 0x100)).substring(1, 3);
        }

        return builder.toString().toUpperCase();
    }
}
