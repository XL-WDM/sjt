package com.stj.business.service;

import com.stj.business.api.dto.AddressDTO;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
public interface IAddressService {

    /**
     * 添加收货地址
     * @param addressDTO
     * @return
     */
    boolean createAddress(AddressDTO addressDTO);
}
