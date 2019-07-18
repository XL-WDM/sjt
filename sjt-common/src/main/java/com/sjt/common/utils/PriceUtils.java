package com.sjt.common.utils;

import com.sjt.common.base.constant.BaseConstant;
import com.sun.prism.impl.BaseContext;

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
    public static Double centToYuan(Long cent) {
        if (cent == null) {
            return null;
        }

        return Double.valueOf((double)cent / BaseConstant.Digit.HUNDRED_DIGIT);
    }
}
