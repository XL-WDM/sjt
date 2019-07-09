package com.stj.common.utils;

import com.stj.common.base.constant.BaseConstant;
import com.stj.common.base.result.ResultModel;
import com.stj.common.exceptions.GlobalException;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * @author: yilan.hu
 * @data: 2019/5/27
 */
public class CheckObjects {

    public static void isNull(Object o, String message) {
        if (o == null) {
            throw new GlobalException(ResultModel.error(message));
        }
    }

    public static void isNull(Object o, ResultModel resultModel) {
        if (StringUtils.isEmpty(o)) {
            throw new GlobalException(resultModel);
        }
    }

    public static void isEmpty(String s, String message) {
        if (StringUtils.isEmpty(s)) {
            throw new GlobalException(ResultModel.error(message));
        }
    }

    public static void isEmpty(String s, ResultModel resultModel) {
        if (StringUtils.isEmpty(s)) {
            throw new GlobalException(resultModel);
        }
    }

    public static void isEmpty(List list, String message) {
        if (list == null || list.isEmpty()) {
            throw new GlobalException(ResultModel.error(message));
        }
    }

    public static void isEmpty(Map map, String message) {
        if (map == null || map.isEmpty()) {
            throw new GlobalException(ResultModel.error(message));
        }
    }

    public static void isStatus(String status, String emptyMessage, String enumNullMessage) {
        isEmpty(status, emptyMessage);
        BaseConstant.Status statusEnum = BaseConstant.Status.find(status);
        isNull(status, enumNullMessage);
    }

    public static <T> void predicate(T t, Predicate<T> predicate, String message) {
        if (predicate.test(t)) {
            throw new GlobalException(message);
        }
    }
}
