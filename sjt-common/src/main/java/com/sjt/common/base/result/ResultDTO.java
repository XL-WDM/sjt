package com.sjt.common.base.result;

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

    private final static Integer SUCCESS_CODE_200 = 200;
    private final static Integer NOT_SIGN_CODE_401 = 401;
    private final static Integer ERROR_CODE_500 = 500;

    private final static String SUCCESS_MESSAGE = "成功";
    private final static String NOT_SIGN_MESSAGE = "用户信息失效, 请重新登录!";
    private final static String ERROR_MESSAGE = "哎呀, 服务开小差啦!";


    @Override
    public ResultDTO put(Object key, Object value) {
        super.put(key, value);
        return this;
    }

    /** 响应码(只用来swagger展示) */
    @ApiModelProperty("响应码")
    private Integer code;

    /** 响应信息(只用来swagger展示) */
    @ApiModelProperty("响应信息")
    private String message;

    /** 响应数据(只用来swagger展示) */
    @ApiModelProperty("响应数据")
    private R data;

    /** 分页数据(只用来swagger展示) */
    @ApiModelProperty("分页数据")
    private PageDTO page;

    public Integer getCode() {
        return (Integer) this.get("code");
    }

    public String getMessage() {
        Object msg = this.get("message");
        return msg == null ? "" : msg.toString();
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
        resultDTO.put("total", total).put("rows", rows);
        if (d != null) {
            resultDTO.put("data", d);
        }
        return resultDTO;
    }
}
