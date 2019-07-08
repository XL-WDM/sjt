package com.stj.business.constant;

import com.stj.business.strategy.sign.mode.SignModeHandler;
import com.stj.business.strategy.sign.mode.impl.PasswordModeSignHandler;
import com.stj.business.strategy.sign.mode.impl.WechatModeSignHandler;
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
         * 登陆方式登陆方式(1-密码登录, 2-短信验证登录, 3-邮箱登陆, 4-微信登陆, 5-QQ登陆, 6.微博登陆)
         */
        SIGN_MODE_PASSWORD("1", "密码登录", PasswordModeSignHandler.class),
        SIGN_MODE_SMS("2", "短信验证登录", PasswordModeSignHandler.class),
        SIGN_MODE_EMAIL("3", "邮箱登陆", PasswordModeSignHandler.class),
        SIGN_MODE_WX("4", "微信登陆", PasswordModeSignHandler.class),
        SIGN_MODE_QQ("5", "QQ登陆", PasswordModeSignHandler.class),
        SIGN_MODE_WB("6", "微博登陆", PasswordModeSignHandler.class);

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

}
