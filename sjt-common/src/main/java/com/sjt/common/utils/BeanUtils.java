package com.sjt.common.utils;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * @author: yilan.hu
 * @data: 2019/4/1
 */
public class BeanUtils {

    /**
     * bean copy
     * @param from
     * @param dtoClazz
     * @return
     */
    public static <From, To> To copyBean(From from, Class<To> dtoClazz) {
        return copyBean(from, dtoClazz, null);
    }

    /**
     * bean copy and ignore
     * @param from
     * @param toClazz
     * @param ignore
     * @return
     */
    public static <From, To> To copyBean(From from, Class<To> toClazz, String ... ignore) {
        if (from == null || toClazz == null) {
            return null;
        }
        try {
            To to = toClazz.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(from, to, ignore);
            return to;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * list copy
     * @param fromList
     * @param toClazz
     * @param <From>
     * @param <To>
     * @return
     */
    public static <From, To> List<To> copyList(List<From> fromList, Class<To> toClazz) {
        return copyList(fromList, toClazz);
    }

    /**
     * list copy and ignore
     * @param fromList
     * @param toClazz
     * @param ignore
     * @param <From>
     * @param <To>
     * @return
     */
    public static <From, To> List<To> copyList(List<From> fromList, Class<To> toClazz, String ... ignore) {
        if (fromList == null || fromList.isEmpty()) {
            return new ArrayList<To>();
        }
        try {
            return fromList.stream().map(from -> {
               return copyBean(from, toClazz, ignore);
            }).collect(toList());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<To>();
        }
    }
}
