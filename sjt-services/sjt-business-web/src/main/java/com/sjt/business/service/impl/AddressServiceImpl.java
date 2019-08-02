package com.sjt.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sjt.business.api.dto.req.AddressParamDTO;
import com.sjt.business.api.dto.res.AddressDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Address;
import com.sjt.business.entity.User;
import com.sjt.business.mapper.AddressMapper;
import com.sjt.business.service.IAddressService;
import com.sjt.business.web.config.WebUserContext;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.base.constant.ResultConstant;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

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
    public List<AddressDTO> getAddressList() {
        // 1.获取登录用户
        User user = WebUserContext.getContext();

        // 2.查询收货地址
        List<Address> address = addressMapper.selectList(new EntityWrapper<Address>()
                .eq("user_id", user.getId())
                .eq("status", BaseConstant.Status.YES.getCode())
                .orderBy("is_default", false)
                .orderBy("create_date", false));

        // 3.Entity - DTO
        List<AddressDTO> addressDTOS = BeanCopierUtils.copyList(address, AddressDTO.class);

        return addressDTOS;
    }

    @Override
    public void createAddress(AddressParamDTO addressParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(addressParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        User user = WebUserContext.getContext();
        CheckObjects.isEmpty(addressParamDTO.getContacts(), "请填写联系人");
        CheckObjects.isEmpty(addressParamDTO.getPhone(), "请填写联系电话");
        CheckObjects.isEmpty(addressParamDTO.getProvince(), "请选择省份");
        CheckObjects.isEmpty(addressParamDTO.getCity(), "请选择城市");
        CheckObjects.isEmpty(addressParamDTO.getCounty(), "请选择县、区");
        CheckObjects.isEmpty(addressParamDTO.getAddress(), "请填写收货地址");
        CheckObjects.isEmpty(addressParamDTO.getDoorNumber(), "请填写门牌号");
        /*String tag = addressParamDTO.getTag();
        CheckObjects.isEmpty(tag, "请选择地址标签");
        DataBaseConstant.AddressTag tagEnum = DataBaseConstant.AddressTag.find(tag);
        CheckObjects.isNull(tagEnum, "地址标签格式有误");
        String call = addressParamDTO.getContactsCall();
        CheckObjects.isEmpty(call, "请选择称呼");
        DataBaseConstant.Call callEnum = DataBaseConstant.Call.find(call);
        CheckObjects.isNull(callEnum, "称呼格式有误");*/
        CheckObjects.isStatus(addressParamDTO.getIsDefault(), "请选择默认地址", "默认地址格式有误");

        // 2.DTO -> Entity
        Address address = BeanCopierUtils.copyBean(addressParamDTO, Address.class);
        address.setUserId(user.getId());
        address.setStatus(BaseConstant.Status.YES.getCode());
        address.setTag(DataBaseConstant.AddressTag.HOME.getCode());
        address.setContactsCall(DataBaseConstant.Call.SIR.getCode());

        // 3.如果新增地址为默认地址, 则更新其他地址为非默认地址
        if (BaseConstant.Status.YES.getCode().equals(addressParamDTO.getIsDefault())) {
            addressMapper.cleanDefaultAddress(user.getId());
        }

        // 4.新增地址
        CheckObjects.predicate(address.insert(), b -> !b, "地址新增失败");
    }

    @Override
    public AddressDTO getAddress(Long id) {
        // 1.参数验证
        CheckObjects.isNull(id, "请选择需要查询的地址");

        // 2.获取当前登录用户
        final User user = WebUserContext.getContext();

        // 3.查询
        Address address = addressMapper.selectAddressByIdAndUserId(id, user.getId());
        CheckObjects.isNull(address, "收货地址不存在");

        // 4.Entity -> DTO
        return BeanCopierUtils.copyBean(address, AddressDTO.class);
    }

    @Override
    public void editAddress(AddressParamDTO addressParamDTO) {
        // 1.参数验证
        CheckObjects.isNull(addressParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        Long id = addressParamDTO.getId();
        CheckObjects.isNull(id, "请选择需要更新的地址");
        /*String tag = addressParamDTO.getTag();
        if (!StringUtils.isEmpty(tag)) {
            CheckObjects.isEmpty(tag, "请选择地址标签");
            DataBaseConstant.AddressTag tagEnum = DataBaseConstant.AddressTag.find(tag);
            CheckObjects.isNull(tagEnum, "地址标签格式有误");
        }
        String call = addressParamDTO.getContactsCall();
        if (!StringUtils.isEmpty(call)) {
            CheckObjects.isEmpty(call, "请选择称呼");
            DataBaseConstant.Call callEnum = DataBaseConstant.Call.find(call);
            CheckObjects.isNull(callEnum, "称呼格式有误");
        }*/
        String isDefault = addressParamDTO.getIsDefault();
        if (!StringUtils.isEmpty(isDefault)) {
            CheckObjects.isStatus(isDefault, "请填写默认地址选项", "默认地址格式有误");
        }

        // 2.获取当前登录用户
        final User user = WebUserContext.getContext();

        // 3.查询
        Address ar = addressMapper.selectAddressByIdAndUserId(id, user.getId());
        CheckObjects.isNull(ar, "该收货地址不存在");

        // 3.如果修改地址为默认地址, 则更新其他地址为非默认地址
        if (BaseConstant.Status.YES.getCode().equals(isDefault)) {
            addressMapper.cleanDefaultAddress(user.getId());
        }

        // 4.更新
        // DTO -> Entity
        Address address = BeanCopierUtils.copyBean(addressParamDTO, Address.class);
        address.setId(id);
        address.setUserId(ar.getUserId());
        address.setUpdateDate(LocalDateTime.now());
        Integer rows = addressMapper.updateById(address);
        CheckObjects.predicate(rows, r -> r == 0, "更新收货地址失败");
    }

    @Override
    public void removeAddress(Long id) {
        // 1.参数验证
        CheckObjects.isNull(id, "请选择需要删除的地址");

        // 2.获取当前登录用户
        final User user = WebUserContext.getContext();

        // 3.查询
        Address address = addressMapper.selectAddressByIdAndUserId(id, user.getId());
        CheckObjects.isNull(address, "该收货地址不存在");

        // 4.修改地址为不可用
        address.setStatus(BaseConstant.Status.NO.getCode());
        CheckObjects.predicate(address.updateById(), b -> !b, "删除收货地址失败");
    }
}
