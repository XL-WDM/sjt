package com.sjt.common.utils;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author: yilan.hu
 * @data: 2019/6/13
 */
public class TypeUtils {

    public static final String TYPE_STRING = "String";
    public static final String TYPE_INTEGER = "Integer";
    public static final String TYPE_LONG = "Long";
    public static final String TYPE_ENUM = "Enum";

    /**
     * 枚举
     * 2|1,2,3
     */
    public static class Enum{
        private static final String S0 = "|";
        private static final String S1 = "\\|";
        private static final String S2 = ",";

        public static boolean isEnum(String value, String... enums) {
            if(value == null || enums == null || enums.length <= 0) {
                return false;
            }

            // 去重
            HashSet<String> sets = new HashSet<>(Arrays.asList(enums));

            for (String e : sets) {
                if(value.equals(e)) {
                    return true;
                }
            }

            return false;
        }

        public static boolean isEnum(String enumStr) {
            if(enumStr == null || enumStr.length() == 0) {
                return false;
            }
            String[] s = enumStr.split(S1);
            if(s.length != (1 << 1) || "".equals(s[0]) || "".equals(s[1])) {
                return false;
            }
            return isEnum(s[0], s[1].split(S2));
        }

        public static String formatString(String value, String...enums) {
            if(value == null || enums == null || enums.length <= 0 || !isEnum(value, enums)) {
                return value;
            }
            StringBuffer sb = new StringBuffer();
            sb.append(value).append(S0);
            for (String e : enums) {
                sb.append(e).append(S2);
            }
            sb.delete(sb.length()-1, sb.length());
            return sb.toString();
        }

        public static String getValue(String enumStr) {
            if(isEnum(enumStr)) {
                String[] ss = enumStr.split(S1);
                return enumStr.split(S1)[0];
            }
            return null;
        }

        public static String[] getEnums(String enumStr) {
            if(isEnum(enumStr)) {
                return enumStr.split(S1)[1].split(S2);
            }
            return null;
        }
    }

    /**
     * 整形
     */
    public static class Integer{
        public static boolean isInteger(String value) {
            try {
                java.lang.Integer.valueOf(value);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

    /**
     * 长整形
     */
    public static class Long{
        public static boolean isLong(String value) {
            try {
                java.lang.Long.valueOf(value);
            } catch (Exception e) {
                return false;
            }
            return true;
        }
    }

    /**
     * 类型验证
     * @param type
     * @param value
     * @param enums
     * @return
     */
    public static boolean typeValidate(String type, String value) {
        if(TYPE_STRING.equals(type)) {
            return true;
        } else if(TYPE_INTEGER.equals(type)) {
            return Integer.isInteger(value);
        } else if(TYPE_LONG.equals(type)) {
            return Long.isLong(value);
        } else if(TYPE_ENUM.equals(type)) {
            return Enum.isEnum(value);
        } else {
            return false;
        }
    }
}
