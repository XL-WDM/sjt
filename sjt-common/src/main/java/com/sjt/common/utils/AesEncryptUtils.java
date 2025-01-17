package com.sjt.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author: yilan.hu
 * @data: 2019/7/17
 */
@Slf4j
public class AesEncryptUtils {
    private static final String DEFAULT_CHARSET = "UTF-8";
    private static final String KEY_AES = "AES";
    private static final String KEY_MD5 = "MD5";
    private static MessageDigest md5Digest;

    static {
        try {
            md5Digest = MessageDigest.getInstance(KEY_MD5);
        } catch (NoSuchAlgorithmException e) {
            //
        }
    }

    /**
     * 加密
     * @param data 需要加密的内容
     * @param key  加密密码
     * @return
     */
    public static String encrypt(String data, String key) {
        return doAES(data, key, Cipher.ENCRYPT_MODE);
    }

    /**
     * 解密
     * @param data 待解密内容
     * @param key  解密密钥
     * @return
     */
    public static String decrypt(String data, String key) {
        return doAES(data, key, Cipher.DECRYPT_MODE);
    }

    /**
     * 加解密
     *
     * @param data
     * @param key
     * @param mode
     * @return
     */
    private static String doAES(String data, String key, int mode) {
        try {
            if (data == null || "".equals(data)) {
                return null;
            }
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            if (encrypt) {
                content = data.getBytes(DEFAULT_CHARSET);
            } else {
                content = Base64.decodeBase64(data.getBytes());
            }
            SecretKeySpec keySpec = new SecretKeySpec(md5Digest.digest(key.getBytes(DEFAULT_CHARSET)), KEY_AES);
            // 创建密码器
            Cipher cipher = Cipher.getInstance(KEY_AES);
            // 初始化
            cipher.init(mode, keySpec);
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                return new String(Base64.encodeBase64(result, false), DEFAULT_CHARSET);
            } else {
                return new String(result, DEFAULT_CHARSET);
            }
        } catch (Exception e) {
            log.error("## AES密文处理异常", e);
        }
        return null;
    }
}
