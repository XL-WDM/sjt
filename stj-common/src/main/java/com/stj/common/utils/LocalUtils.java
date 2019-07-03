package com.stj.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author: yilan.hu
 * @data: 2019/6/6
 */
@Slf4j
public class LocalUtils {

    private static InetAddress inet;

    static {
        try {
            inet = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            log.error("## LocalAddressUtils loading localhost fail", e);
        }
    }

    public static String getIp() {
        return inet != null ? inet.getHostAddress() : null;
    }
}
