package com.stj.business.api.impl;

import com.stj.business.api.dto.req.AddressParamDTO;
import com.stj.business.api.dto.res.AddressDTO;
import com.stj.business.api.expose.AddressApi;
import com.stj.business.service.IAddressService;
import com.stj.common.base.result.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@RestController
public class AddressApiService implements AddressApi {

    @Autowired
    private IAddressService iAddressService;

    @Override
    public ResultModel<List<AddressDTO>> getAddressList() {
        List<AddressDTO> addressDTOS = iAddressService.getAddressList();
        return ResultModel.data(addressDTOS);
    }

    @Override
    public ResultModel createAddress(AddressParamDTO addressDTO) {
        iAddressService.createAddress(addressDTO);
        return ResultModel.success();
    }

    @Override
    public ResultModel<AddressDTO> getAddress(Long id) {
        AddressDTO address = iAddressService.getAddress(id);
        return ResultModel.data(address);
    }

    @Override
    public ResultModel editAddress(AddressParamDTO addressDTO) {
        iAddressService.editAddress(addressDTO);
        return ResultModel.success();
    }

    @Override
    public ResultModel removeAddress(Long id) {
        iAddressService.removeAddress(id);
        return ResultModel.success();
    }
}
