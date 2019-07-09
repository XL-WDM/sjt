package com.stj.business.api.expose;

import com.stj.business.api.dto.AddressDTO;
import com.stj.common.base.result.ResultModel;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Api(description = "收货地址管理")
@RequestMapping("/address")
public interface AddressApi {

    /**
     * 新增收货地址
     * @param addressDTO
     * @return
     */
    @PostMapping("/address")
    ResultModel createAddress(AddressDTO addressDTO);

}
