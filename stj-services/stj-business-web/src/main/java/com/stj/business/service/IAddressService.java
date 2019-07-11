package com.stj.business.service;

import com.stj.business.api.dto.req.AddressParamDTO;
import com.stj.business.api.dto.res.AddressDTO;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
public interface IAddressService {

    /**
     * 获取用户所有收货地址
     * @return
     */
    List<AddressDTO> getAddressList();

    /**
     * 添加收货地址
     * @param addressParamDTO
     * @return
     */
    void createAddress(AddressParamDTO addressParamDTO);

    /**
     * 获取收货地址详情
     * @param id
     * @return
     */
    AddressDTO getAddress(Long id);

    /**
     * 编辑收货地址
     * @param id
     * @param addressParamDTO
     */
    void editAddress(AddressParamDTO addressParamDTO);

    /**
     * 删除收货地址
     * @param id
     */
    void removeAddress(Long id);
}
