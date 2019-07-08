package com.stj.common.exceptions;

import com.stj.common.base.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 统一异常处理
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@ControllerAdvice
@Slf4j
public class GlobaleExcepitonHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public R handler(Exception e) {
        if (e instanceof GlobalException) {
            GlobalException ge = (GlobalException) e;
            return R.info(ge.getCode(), ge.getMessage());
        } else if (e instanceof HttpRequestMethodNotSupportedException) {
            return R.error(e.getLocalizedMessage());
        } else {
            log.error("# 系统异常", e);
            return R.error();
        }
    }
}
