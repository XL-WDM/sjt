package com.stj.common.exceptions;

import com.stj.common.base.result.R;

/**
 * 全局异常
 * @author: yilan.hu
 * @data: 2019/7/3
 */
public class GlobalException extends RuntimeException {
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

    public GlobalException(R r) {
        super((r = (r != null ? r : R.error())).getMessage());
        this.code = r.getCode();
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
