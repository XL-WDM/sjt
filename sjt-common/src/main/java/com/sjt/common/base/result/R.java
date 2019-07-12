package com.sjt.common.base.result;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
public class R<T> extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 3629991043482168201L;

    private final static String SUCCESS_MESSAGE = "成功";
    private final static String ERROR_MESSAGE = "哎呀, 服务开小差啦!";

    private final static Integer CODE_200 = 200;
    private final static Integer CODE_401 = 401;
    private final static Integer CODE_404 = 404;
    private final static Integer CODE_500 = 500;

    @Override
    public R put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {
        return (Integer)get("code");
    }

    public String getMessage() {
        Object message = get("message");
        return message != null ? message.toString() : "";
    }

    public static R info(Integer code, String message) {
        return new R().put("code", code).put("message", message);
    }

    public static R error(String message) {
        return R.info(CODE_500, message);
    }

    public static R error() {
        return R.error(ERROR_MESSAGE);
    }

    public static R success(String message) {
        return R.info(CODE_200, message);
    }

    public static R success() {
        return R.success(SUCCESS_MESSAGE);
    }

    public static <D> R<D> data(D d) {
        return R.success().put("data", d);
    }

    public static <D> R<D> page(Integer total, List<D> rows) {
        return R.success().put("total", total).put("rows", rows);
    }
}
