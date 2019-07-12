package com.sjt.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sjt.business.entity.Address;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
public interface AddressMapper extends BaseMapper<Address> {

    /**
     * 通过收货地址id和用户id查询
     * @param address
     * @return
     */
    Address selectAddressByIdAndUserId(Address address);

    /**
     * 修改为非默认地址
     * @param userId
     */
    void cleanDefaultAddress(Long userId);
}
