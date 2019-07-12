package com.sjt.common.utils;

/**
 * @author: yilan.hu
 * @data: 2019/6/24
 */
public class CharUtils {

    /**
     * \n -> '<br/>'
     * @param lnStr
     * @return
     */
    public static String lnToBr(String lnStr) {
        return lnStr == null ? null : lnStr.replaceAll("(\\\\n)", "<br/>");
    }

    /**
     * enter -> '<br/>'
     * @param enterStr
     * @return
     */
    public static String enterToBr(String enterStr) {
        return enterStr == null ? null : enterStr.replaceAll("(\\n)", "<br/>");
    }

    /**
     * '<br/>' -> \n
     * @param brStr
     * @return
     */
    public static String brToLn(String brStr) {
        return brStr == null ? null : brStr.replaceAll("<br/>", "\\\\n");
    }
}
