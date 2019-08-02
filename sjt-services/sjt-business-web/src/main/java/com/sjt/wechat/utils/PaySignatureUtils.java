package com.sjt.wechat.utils;

import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.utils.MD5Utils;
import org.springframework.util.StringUtils;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * 支付签名工具类
 * @author: yilan.hu
 * @data: 2019/8/2
 */
public class PaySignatureUtils {

    /**
     * 微信生成签名
     * @param map
     * @param signKey
     * @return
     */
    public static String wxSign(Map<String, String> map, String signKey) {
        if (map == null || StringUtils.isEmpty(signKey)) {
            return null;
        }

        StringBuilder builder = new StringBuilder();

        Set<Map.Entry<String, String>> entries = new TreeMap<>(map).entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
            String key = next.getKey();
            String value = next.getValue();
            if (StringUtils.isEmpty(key) || "sign".equals(key) || StringUtils.isEmpty(value)) {
                continue;
            }

            builder.append(key)
                    .append("=")
                    .append(value)
                    .append(BaseConstant.Character.AND);
        }

        builder.append("key=").append(signKey);

        return MD5Utils.getMD5(builder.toString()).toUpperCase();
    }

    /**
     * 微信验签
     * @param params
     * @param signKey
     * @return
     */
    public static Boolean vxVerify(Map<String, String> params, String signKey) {
        String sign = wxSign(params, signKey);
        return sign.equals(params.get("sign"));
    }
}
