package com.sjt.common.utils;

import java.util.Random;

/**
 * @author: yilan.hu
 * @data: 2019/8/2
 */
public class RandomUtils {

    private static final String RANDOM_STR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final Random RANDOM = new Random();

    public static String getRandomString() {
        return getRandomString(16);
    }

    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < length; ++i) {
            sb.append(RANDOM_STR.charAt(RANDOM.nextInt(RANDOM_STR.length())));
        }

        return sb.toString();
    }
}
