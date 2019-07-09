package com.stj.business.constant;

import com.stj.business.strategy.sign.mode.SignModeHandler;
import com.stj.business.strategy.sign.mode.impl.PasswordModeSignHandler;
import com.stj.common.utils.SpringUtils;
import com.stj.common.utils.StringTools;
import lombok.Getter;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public class DataBaseConstant {

    @Getter
    public enum SignMode {
        /**
         * 登录方式登录方式(1-密码登录, 2-短信验证登录, 3-邮箱登录, 4-微信登录, 5-QQ登录, 6.微博登录)
         */
        SIGN_MODE_PASSWORD("1", "密码登录", PasswordModeSignHandler.class),
        SIGN_MODE_SMS("2", "短信验证登录", PasswordModeSignHandler.class),
        SIGN_MODE_EMAIL("3", "邮箱登录", PasswordModeSignHandler.class),
        SIGN_MODE_WX("4", "微信登录", PasswordModeSignHandler.class),
        SIGN_MODE_QQ("5", "QQ登录", PasswordModeSignHandler.class),
        SIGN_MODE_WB("6", "微博登录", PasswordModeSignHandler.class);

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

        public SignModeHandler getHandler() {
            if (handler == null) {
                String beanName = StringTools.firstToLowerCase(this.handlerClazz.getSimpleName());
                handler = (SignModeHandler)SpringUtils.getBean(beanName);
            }
            return handler;
        }
    }


    @Getter
    public enum AddressTag {
        /**
         * 地址标签(1-家, 2-公司, 3-学校)
         */
        HOME("1", "家"),
        COMPANY("2", "公司"),
        SCHOOL("3", "学校");

        private AddressTag(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public static AddressTag find(String code) {
            for (AddressTag tag : values()) {
                if (tag.getCode().equals(code)) {
                    return tag;
                }
            }
            return null;
        }
    }

    @Getter
    public enum Call {
        /**
         * 称呼(1-先生, 2-女士)
         */
        SIR("1", "先生"),
        LADY("2", "女士");

        private Call(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public static Call find(String code) {
            for (Call call : values()) {
                if (call.getCode().equals(code)) {
                    return call;
                }
            }
            return null;
        }
    }
}
