package com.sjt.common.utils;

/**
 * @author: yilan.hu
 * @data: 2019/5/27
 */
public class StringTools {

    private final static int CHAR_65 = 65;
    private final static int CHAR_90 = 90;
    private final static int CHAR_97 = 97;
    private final static int CHAR_122 = 122;

    /**
     * 首字母转大写
     * @param value
     * @return
     */
    public static String firstToUpperCase(String value) {
        if (value == null) {
            return null;
        }

        byte[] bytes = value.getBytes();
        byte b = bytes[0];

        if (b >= CHAR_97 &&  b <= CHAR_122 ) {
            bytes[0] -= 32;
        }
        return new String(bytes);
    }

    /**
     * 首字母转小写
     * @param value
     * @return
     */
    public static String firstToLowerCase(String value) {
        if (value == null) {
            return null;
        }

        byte[] bytes = value.getBytes();
        byte b = bytes[0];

        if(b <= CHAR_90 && b >= CHAR_65){
            bytes[0] += 32;
        }
        return new String(bytes);
    }
}
