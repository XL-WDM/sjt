package com.stj.business.service.impl;

import com.stj.business.api.dto.AddressDTO;
import com.stj.business.mapper.AddressMapper;
import com.stj.business.service.IAddressService;
import com.stj.common.base.constant.ResultConstant;
import com.stj.common.utils.CheckObjects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Slf4j
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public boolean createAddress(AddressDTO addressDTO) {
        // 1.参数校验
        CheckObjects.isNull(addressDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);


        return false;
    }
}
