package com.stj.business.api.impl;

import com.stj.business.api.dto.AddressDTO;
import com.stj.business.api.expose.AddressApi;
import com.stj.common.base.result.ResultModel;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@RestController
public class AddressApiService implements AddressApi {

    @Override
    public ResultModel createAddress(AddressDTO addressDTO) {
        return null;
    }
}
