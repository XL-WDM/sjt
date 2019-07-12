package com.sjt.common.exceptions;

import com.sjt.common.base.result.R;
import com.sjt.common.base.result.ResultModel;

/**
 * 全局异常
 * @author: yilan.hu
 * @data: 2019/7/3
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = -4617529608129051364L;

    /**
     * 代码
     */
    private int code;

    public GlobalException(String message) {
        super(message);
        this.code = 500;
    }

    public GlobalException(int code, String message) {
        super(message);
        this.code = code;
    }

    public GlobalException(ResultModel resultModel) {
        super((resultModel = (resultModel != null ? resultModel : ResultModel.error())).getMessage());
        this.code = resultModel.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
