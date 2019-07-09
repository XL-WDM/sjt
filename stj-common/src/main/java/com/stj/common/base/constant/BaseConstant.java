package com.stj.common.base.constant;

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
        public final static String JAVA_FILE_SUFFIX = ".java";
        public final static String CLASS_FILE_SUFFIX = ".class";
        public final static String XML_FILE_SUFFIX = ".xml";
        public final static String PNG_FILE_SUFFIX = ".png";
        public final static String JPG_FILE_SUFFIX = ".jpg";
        public final static String JPEG_FILE_SUFFIX = ".jpeg";
        public final static String SVG_FILE_SUFFIX = ".svg";
        public final static String GIF_FILE_SUFFIX = ".gif";
        public final static String XLS_FILE_SUFFIX = ".xls";
        public final static String XLSX_FILE_SUFFIX = ".xlsx";
    }

    /**
     * 基本字符
     */
    public static class Character {
        public final static String SLASH = "/";
        public final static String UNDERLINE = "_";
        public final static String BAR = "-";
        public final static String COMMA = ",";
        public final static String GET = "get";
        public final static String SET = "set";
    }

    /**
     * 时间(毫秒)
     */
    public static class Time {
        public final static long SECOND = 1000;
        public final static long MINUTE = 60 * SECOND;
        public final static long HOUR = 60 * MINUTE;
        public final static long DAY = 24 * MINUTE;
    }

    /**
     * 时间(秒)
     */
    public static class Second {
        public final static int SECOND = 1;
        public final static int MINUTE = 60 * SECOND;
        public final static int HOUR = 60 * MINUTE;
        public final static int DAY = 24 * HOUR;
    }

    /**
     * 时间格式常量
     */
    public static class FormatDate {
        public final static String SIMPLE_DATE = "yyyyMMdd";
        public final static String SIMPLE_DATE_TIME = "yyyyMMddHHmmss";
        public final static String DATE = "yyyy-MM-dd";
        public final static String DATE_TIME = "yyyy-MM-dd HH:mm:ss";
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
