package com.sjt.common.base.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
@ApiModel("响应对象")
@Data
public class ResultDTO<R> implements Serializable {

    private static final long serialVersionUID = 834150395386082650L;

    private final static Integer SUCCESS_CODE_200 = 200;
    private final static Integer NOT_SIGN_CODE_401 = 401;
    private final static Integer ERROR_CODE_500 = 500;

    private final static String SUCCESS_MESSAGE = "成功";
    private final static String NOT_SIGN_MESSAGE = "用户信息失效, 请重新登录!";
    private final static String ERROR_MESSAGE = "哎呀, 服务开小差啦!";

    /**
     * 响应码
     */
    @ApiModelProperty("响应码")
    private Integer code;

    /**
     * 响应信息
     */
    @ApiModelProperty("响应信息")
    private String message;

    /**
     * 响应数据
     */
    @ApiModelProperty("响应数据")
    private R data;

    public static ResultDTO info(Integer code, String message) {
        ResultDTO result = new ResultDTO();
        result.setCode(code);
        result.setMessage(message);
        return result;
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
        ResultDTO result = ResultDTO.success();
        result.setData(data);
        return result;
    }

    public static ResultDTO page(int total, List rows) {
        return ResultDTO.data(new PageDTO(total, rows));
    }
}
