package com.sjt.business.constant;

import com.sjt.business.strategy.sign.mode.SignModeHandler;
import com.sjt.business.strategy.sign.mode.impl.*;
import com.sjt.common.utils.SpringUtils;
import com.sjt.common.utils.StringTools;
import lombok.Getter;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public class DataBaseConstant {

    @Getter
    public enum SignMode {
        /**
         * 登录方式登录方式(1-密码登录, 2-短信验证登录, 3-邮箱登录, 4-QQ登录, 5-微博登录, 6-微信小程序, 7-微信公众号登录)
         */
        SIGN_MODE_PASSWORD("1", "密码登录", PasswordModeSignHandler.class),
        SIGN_MODE_SMS("2", "短信验证登录", SmsModeSignHandler.class),
        SIGN_MODE_EMAIL("3", "邮箱登录", EmailModeSignHandler.class),
        SIGN_MODE_QQ("4", "QQ登录", QqModeSignHandler.class),
        SIGN_MODE_WB("5", "微博登录", WbmodeSignHandler.class),
        SIGN_MODE_WX_SMALL_PROCEDURES("6", "微信小程序", WxSmallProceduresSignHandler.class),
        SIGN_MODE_WX("7", "微信公众号登录", WechatModeSignHandler.class);

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

    @Getter
    public enum OauthType {
        /**
         * 授权类型(1-微信小程序, 2-微信公众号, 3-微信开放平台, 4-QQ, 5-微博)
         */
        WX_APPLET("1", "微信小程序"),
        WX_PUBLIC_NUMBER("2", "微信公众号"),
        WX_OPEN_PLATFORM("3", "微信开放平台"),
        QQ("4", "QQ"),
        MICRO_BLOG("5", "微博");

        private OauthType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public static OauthType find(String code) {
            for (OauthType type : values()) {
                if (type.getCode().equals(code)) {
                    return type;
                }
            }
            return null;
        }
    }

    @Getter
    public enum BannerType {
        /**
         * banner类型(1-首页top轮播图, 2-GIF小视频, 3-山田日记banner, 4-首页center轮播图)
         */
        TOP_BANNER("1", "首页top轮播图"),
        GIF_BANNER("2", "GIF小视频"),
        ST_NOTES_BANNER("3", "山田日记banner"),
        CENTER_BANNER("4", "首页中部轮播图");

        private BannerType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public static BannerType find(String code) {
            for (BannerType type : values()) {
                if (type.getCode().equals(code)) {
                    return type;
                }
            }
            return null;
        }
    }
}
