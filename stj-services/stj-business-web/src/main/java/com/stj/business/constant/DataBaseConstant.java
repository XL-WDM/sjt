package com.stj.business.constant;

import com.stj.business.strategy.sign.mode.SignModeHandler;
import com.stj.business.strategy.sign.mode.impl.PasswordModeSignHandler;
import lombok.Getter;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public class DataBaseConstant {

    @Getter
    public enum SignMode {
        /**
         * 登陆方式(1-密码登录, 2-短信验证登录, 3-第三方授权登陆, 4-邮箱登陆)
         */
        SIGN_MODE_PASSWORD("1", "密码登录", PasswordModeSignHandler.class),
        SIGN_MODE_SMS("2", "短信验证登录", PasswordModeSignHandler.class),
        SIGN_MODE_OAUTH("3", "第三方授权登陆", PasswordModeSignHandler.class),
        SIGN_MODE_EMAIL("4", "邮箱登陆", PasswordModeSignHandler.class);

        SignMode(String code, String mode, Class<? extends SignModeHandler> handlerClazz) {
            this.code = code;
            this.mode = mode;
            this.handlerClazz = handlerClazz;
        }

        private String code;
        private String mode;
        private Class<? extends SignModeHandler> handlerClazz;
        private SignModeHandler handler;

        public static SignMode find(String code) {
            for (SignMode signMode : values()) {
                if (signMode.getCode().equals(code)) {
                    return signMode;
                }
            }
            return null;
        }
    }

}
