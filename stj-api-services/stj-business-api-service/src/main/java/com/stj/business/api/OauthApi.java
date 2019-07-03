package com.stj.business.api;

import com.stj.common.base.result.R;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yilan.hu
 * @data: 2019/7/1
 */
@Api(description = "授权")
@RequestMapping("/auth")
public interface OauthApi {

    @ApiOperation(value = "认证")
    @PostMapping("/oauth")
    R oauth();
}
