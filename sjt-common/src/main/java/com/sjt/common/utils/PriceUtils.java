package com.sjt.common.utils;

import java.math.BigDecimal;

/**
 * 金额工具类
 * @author: yilan.hu
 * @data: 2019/7/11
 */
public class PriceUtils {

    /**
     * 分 -> 元
     * @param cent
     * @return
     */
    public static BigDecimal centToYuan(BigDecimal cent) {
        if (cent == null) {
            return null;
        }

        return cent.multiply(BigDecimal.valueOf(0.01));
    }
}
