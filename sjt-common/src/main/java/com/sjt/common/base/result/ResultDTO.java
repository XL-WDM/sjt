package com.sjt.common.base.result;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@ApiModel("响应对象")
public class ResultDTO<R> extends HashMap implements Serializable {

    private static final long serialVersionUID = 834150395386082650L;

    private static final Integer SUCCESS_CODE_200 = 200;
    private static final Integer NOT_SIGN_CODE_401 = 401;
    private static final Integer ERROR_CODE_500 = 500;

    private static final String SUCCESS_MESSAGE = "成功";
    private static final String NOT_SIGN_MESSAGE = "用户信息失效, 请重新登录!";
    private static final String ERROR_MESSAGE = "哎呀, 服务开小差啦!";


    @Override
    public ResultDTO put(Object key, Object value) {
        super.put(key, value);
        return this;
    }

    public Integer getCode() {
        return (Integer) this.get("code");
    }

    public String getMessage() {
        Object msg = this.get("message");
        return msg == null ? "" : msg.toString();
    }

    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }

    public static ResultDTO info(Integer code, String message) {
        return new ResultDTO().put("code", code).put("message", message);
    }

    public static ResultDTO error(String message) {
        return ResultDTO.info(ERROR_CODE_500, message);
    }

    public static ResultDTO error() {
        return ResultDTO.info(ERROR_CODE_500, ERROR_MESSAGE);
    }

    public static ResultDTO error401() {
        return ResultDTO.info(NOT_SIGN_CODE_401, NOT_SIGN_MESSAGE);
    }

    public static ResultDTO success(String message) {
        return ResultDTO.info(SUCCESS_CODE_200, message);
    }

    public static ResultDTO success() {
        return ResultDTO.success(SUCCESS_MESSAGE);
    }

    public static <D> ResultDTO data(D data) {
        return ResultDTO.success().put("data", data);
    }

    public static ResultDTO page(int total, List rows) {
        return ResultDTO.page(total, rows, null);
    }

    public static <D> ResultDTO page(int total, List rows, D d) {
        ResultDTO resultDTO = ResultDTO.success();
        resultDTO.put("page", new PageDTO(total, rows));
        if (d != null) {
            resultDTO.put("data", d);
        }
        return resultDTO;
    }
}
