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
        SIGN_MODE_WX_SMALL_PROCEDURES("6", "微信小程序", WxAppletSignHandler.class),
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
         * 授权类型(1-微信小程序, 2-微信公众号 or 微信开放平台, 3-QQ, 4-微博)
         */
        WX_APPLET("1", "微信小程序"),
        WX_PUBLIC_NUMBER("2", "微信公众号 or 微信开放平台"),
        QQ("3", "QQ"),
        MICRO_BLOG("4", "微博");

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

    @Getter
    public enum ProductPushStatus {
        /**
         * 商品发布状态(0-删除, 1-上架, 2-下架)
         */
        INVALID("0", "删除"),
        UPPER_SHELF("1", "上架"),
        LOWER_SHELF("2", "下架");

        private ProductPushStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public static ProductPushStatus find(String code) {
            for (ProductPushStatus status : values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
            return null;
        }
    }

    @Getter
    public enum ProductSpecType {
        /**
         * 规格类型(1-单规格, 2-多规格)
         */
        SINGLE_SPEC("1", "单规格"),
        MULTI_SPEC("2", "多规格");

        private ProductSpecType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public static ProductSpecType find(String code) {
            for (ProductSpecType type : values()) {
                if (type.getCode().equals(code)) {
                    return type;
                }
            }
            return null;
        }
    }

    @Getter
    public enum OrderStatus {
        /**
         * 订单状态(1-待支付, 2-待发货, 3-待收货, 4-已完成, 5-已取消)
         */
        TO_BE_PAID("1", "待支付"),
        TO_BE_SHIPPED("2", "待发货"),
        TO_BE_RECEIVED("3", "待收货"),
        COMPLETED("4", "已完成"),
        CANCELLED("5", "已取消");

        private OrderStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public static OrderStatus find(String code) {
            for (OrderStatus status : values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
            return null;
        }
    }

    @Getter
    public enum PayType {
        /**
         * 支付类型(1-微信, 2-支付, 3-银联)
         */
        WECHAT("1", "微信"),
        ALIPAY("2", "支付宝"),
        UNIONPAY("3", "银联");

        private PayType(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public static PayType find(String code) {
            for (PayType type : values()) {
                if (type.getCode().equals(code)) {
                    return type;
                }
            }
            return null;
        }
    }

    @Getter
    public enum PayStatus {
        /**
         * 支付状态(0-取消, 1-未支付, 2-已支付, 3-支付异常)
         */
        CANCEL_PAY("1", "取消"),
        UNPAID("2", "未支付"),
        PAYMENTED("3", "已支付"),
        ABNORMAL_PAYMENT("4", "支付异常");

        private PayStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public static PayStatus find(String code) {
            for (PayStatus status : values()) {
                if (status.getCode().equals(code)) {
                    return status;
                }
            }
            return null;
        }
    }

    @Getter
    public enum ProductCategoryLevel {
        /**
         * 商品分类级别(1-一级分类, 2-二级分类, 3-三级分类)
         */
        Category_Level_ONE("1", "一级分类"),
        Category_Level_TWO("2", "二级分类"),
        Category_Level_THREE("3", "三级分类");

        private ProductCategoryLevel(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;
        private String name;

        public static ProductCategoryLevel find(String code) {
            for (ProductCategoryLevel level : values()) {
                if (level.getCode().equals(code)) {
                    return level;
                }
            }
            return null;
        }
    }
}
