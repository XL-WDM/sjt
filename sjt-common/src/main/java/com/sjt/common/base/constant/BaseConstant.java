package com.sjt.common.base.constant;

import lombok.Getter;

/**
 * 基础常量信息
 * @author: yilan.hu
 * @data: 2019/5/24
 */
public class BaseConstant {
    /**
     * 文件
     */
    public static class File {
        public static final String JAVA_FILE_SUFFIX = ".java";
        public static final String CLASS_FILE_SUFFIX = ".class";
        public static final String XML_FILE_SUFFIX = ".xml";
        public static final String PNG_FILE_SUFFIX = ".png";
        public static final String JPG_FILE_SUFFIX = ".jpg";
        public static final String JPEG_FILE_SUFFIX = ".jpeg";
        public static final String SVG_FILE_SUFFIX = ".svg";
        public static final String GIF_FILE_SUFFIX = ".gif";
        public static final String XLS_FILE_SUFFIX = ".xls";
        public static final String XLSX_FILE_SUFFIX = ".xlsx";
    }

    /**
     * 基本字符
     */
    public static class Character {
        public static final String SLASH = "/";
        public static final String UNDERLINE = "_";
        public static final String BAR = "-";
        public static final String COMMA = ",";
        public static final String AND = "&";
        public static final String GET = "get";
        public static final String SET = "set";
        public static final String MD5 = "MD5";
    }

    /**
     * 转译
     */
    public static class Unicode {
        public static final String EQUAL = "\\u003d";
    }

    /**
     * 币种
     */
    public static class FeeType {
        /** 人民币 */
        public static final String CNY = "CNY";
    }

    /**
     * 时间(毫秒)
     */
    public static class Time {
        public static final long SECOND = 1000;
        public static final long MINUTE = 60 * SECOND;
        public static final long HOUR = 60 * MINUTE;
        public static final long DAY = 24 * MINUTE;
    }

    /**
     * 时间(秒)
     */
    public static class Second {
        public static final int SECOND = 1;
        public static final int MINUTE = 60 * SECOND;
        public static final int HOUR = 60 * MINUTE;
        public static final int DAY = 24 * HOUR;
    }

    /**
     * 时间格式常量
     */
    public static class FormatDate {
        public static final String SIMPLE_DATE = "yyyyMMdd";
        public static final String SIMPLE_DATE_TIME = "yyyyMMddHHmmss";
        public static final String DATE = "yyyy-MM-dd";
        public static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    }

    /**
     * 位数
     */
    public static class Digit {
        /**
         * 十分位
         */
        public static final Integer TEN_DIGITS = 10;

        /**
         * 百分位
         */
        public static final Integer HUNDRED_DIGIT = 100;

        /**
         * 千分位
         */
        public static final Integer THOUSAND_DIGIT = 1000;
    }

    @Getter
    public enum Status {
        /**
         * 状态(0-否 1-是)
         */
        NO("0", "否"),
        YES("1", "是");

        private Status(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public static Status find(String code) {
            for (Status status : values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
            return null;
        }
    }
}
