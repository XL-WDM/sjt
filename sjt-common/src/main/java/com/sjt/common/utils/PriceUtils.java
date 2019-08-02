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

    /**
     * 对于单位分的金额进行细微比较(例如: 1.0 == 1.00000000000000001)
     * @param price1
     * @param price2
     * @return
     */
    public static boolean centFineEquals(Double price1, Double price2) {
        if (price1 == null || price2 == null) {
            return false;
        }

        System.out.println(new BigDecimal(price1).subtract(new BigDecimal(price2)).doubleValue());

        return Math.abs(new BigDecimal(price1).subtract(new BigDecimal(price2)).doubleValue()) < 1;
    }
}
