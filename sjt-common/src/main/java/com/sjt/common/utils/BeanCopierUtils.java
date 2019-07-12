package com.sjt.common.utils;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * cglib bean对象copy
 * @author: yilan.hu
 * @data: 2019/5/27
 */
public class BeanCopierUtils {

    private final static String SET = "set";
    private final static String BAR = "_";
    private final static String NONE_TYPE = "None";
    private final static String CONVERTER_TYPE = "Converter";
    private final static String IGNORE_TYPE = "Ignore";

    /**
     * cache
     */
    private static Map<String, BeanCopier> copys = new ConcurrentHashMap<>(64);

    public static <From, To> List<To> copyList(List<From> fromList, Class<To> to) {
        return copyList(fromList, to, null, null);
    }

    public static <From, To> List<To> copyList(List<From> fromList, Class<To> to, Converter converter) {
        return copyList(fromList, to, converter, null);
    }

    public static <From, To> List<To> copyList(List<From> fromList, Class<To> to, String ... ignore) {
        return copyList(fromList, to, null, ignore);
    }

    private static <From, To> List<To> copyList(List<From> fromList, Class<To> to, Converter converter, String ... ignore) {
        if (fromList == null || fromList.isEmpty()) {
            return new ArrayList<>();
        }

        List<To> toList = new ArrayList<>();
        for (From from : fromList) {
            toList.add(copyBean(from, to, converter, ignore));
        }
        return toList;
    }

    public static <From, To> To copyBean(Object from, Class<To> to) {
        return copyBean(from, to, null, null);
    }

    public static <From, To> To copyBean(Object from, Class<To> to, Converter converter) {
        return copyBean(from, to, converter, null);
    }

    public static <From, To> To copyBean(Object from, Class<To> to, String ... ignore) {
        return copyBean(from, to, null, ignore);
    }

    private static <From, To> To copyBean(Object from, Class<To> to, Converter converter, String ... ignore) {
        if (from == null || to == null) {
            return null;
        }

        boolean useConverter = converter != null;
        boolean useIgnoreConverter = ignore != null && ignore.length > 0;

        String type = useConverter ? CONVERTER_TYPE : (useIgnoreConverter ? IGNORE_TYPE : NONE_TYPE);
        if (IGNORE_TYPE.equals(type)) {
            converter = new IgnoreConverter(ignore);
        }

        // 获取完整路径
        String fromName = from.getClass().getName();
        String toName = to.getName();
        String fromToName = fromName + BAR + toName + BAR + type;

        // 验证是否存在、不存在则创建
        if (copys.get(fromToName) == null) {
            copys.put(fromToName, BeanCopier.create(from.getClass(), to, useConverter || useIgnoreConverter));
        }

        To toBean = null;
        try {
            toBean = to.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("目标类没有提供无参构造器");
        }

        BeanCopier beanCopier = copys.get(fromToName);
        beanCopier.copy(from, toBean, converter);
        return toBean;
    }

    private static class IgnoreConverter implements Converter {
        private final static int CHAR_97 = 97;
        private final static int CHAR_122 = 122;
        private final String[] ignores;

        public IgnoreConverter(String[] ignores) {
            this.ignores = ignores;
        }

        @Override
        public Object convert(Object o, Class aClass, Object fieldName) {
            if (ignores != null && ignores.length > 0) {
                for (String ignore : ignores) {
                    String setIgnore = firstToUpperCase(ignore);
                    if ((SET + setIgnore).equals(fieldName.toString())) {
                        return null;
                    }
                }
            }
            return o;
        }

        /**
         * 首字母转大写
         * @param value
         * @return
         */
        public String firstToUpperCase(String value) {
            if (value == null) {
                return null;
            }

            byte[] bytes = value.getBytes();

            byte b;
            if ((b = bytes[0]) >= CHAR_97 &&  b <= CHAR_122 ) {
                bytes[0] -= 32;
            }
            return new String(bytes);
        }
    }
}
