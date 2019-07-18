package com.sjt.common.utils;

import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.base.result.ResultDTO;
import com.sjt.common.exceptions.GlobalException;
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
            throw new GlobalException(ResultDTO.error(message));
        }
    }

    public static void isNull(Object o, ResultDTO resultModel) {
        if (StringUtils.isEmpty(o)) {
            throw new GlobalException(resultModel);
        }
    }

    public static void isEmpty(String s, String message) {
        if (StringUtils.isEmpty(s)) {
            throw new GlobalException(ResultDTO.error(message));
        }
    }

    public static void isEmpty(String s, ResultDTO resultModel) {
        if (StringUtils.isEmpty(s)) {
            throw new GlobalException(resultModel);
        }
    }

    public static void isEmpty(List list, String message) {
        if (list == null || list.isEmpty()) {
            throw new GlobalException(ResultDTO.error(message));
        }
    }

    public static void isEmpty(Map map, String message) {
        if (map == null || map.isEmpty()) {
            throw new GlobalException(ResultDTO.error(message));
        }
    }

    public static void isPage(Integer pageNo, Integer pageSize) {
        if (pageNo == null || pageNo == 0 || pageSize == null || pageSize == 0) {
            throw new GlobalException(ResultDTO.error("分页参数有误"));
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
