package com.sjt.business.api.impl;

import com.sjt.business.api.dto.req.AddressParamDTO;
import com.sjt.business.api.dto.res.AddressDTO;
import com.sjt.business.api.expose.AddressApi;
import com.sjt.business.service.IAddressService;
import com.sjt.common.base.result.ResultDTO;
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
    public ResultDTO<List<AddressDTO>> getAddressList() {
        List<AddressDTO> addressDTOS = iAddressService.getAddressList();
        return ResultDTO.data(addressDTOS);
    }

    @Override
    public ResultDTO createAddress(AddressParamDTO addressDTO) {
        iAddressService.createAddress(addressDTO);
        return ResultDTO.success();
    }

    @Override
    public ResultDTO<AddressDTO> getAddress(Long id) {
        AddressDTO address = iAddressService.getAddress(id);
        return ResultDTO.data(address);
    }

    @Override
    public ResultDTO editAddress(AddressParamDTO addressDTO) {
        iAddressService.editAddress(addressDTO);
        return ResultDTO.success();
    }

    @Override
    public ResultDTO removeAddress(Long id) {
        iAddressService.removeAddress(id);
        return ResultDTO.success();
    }
}
