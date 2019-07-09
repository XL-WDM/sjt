package com.stj.business.api.expose;

import com.stj.business.api.dto.req.AddressParamDTO;
import com.stj.business.api.dto.res.AddressDTO;
import com.stj.common.base.result.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Api(description = "收货地址管理")
@RequestMapping("/address")
public interface AddressApi {

    /**
     * 获取收货地址
     * @return
     */
    @ApiOperation(value = "获取收货地址列表", response = AddressDTO.class)
    @GetMapping("/list/all")
    ResultModel<List<AddressDTO>> getAddressList();

    /**
     * 新增收货地址
     * @param addressDTO
     * @return
     */
    @ApiOperation(value = "新增收货地址")
    @PostMapping("/add")
    ResultModel createAddress(AddressParamDTO addressDTO);

    /**
     * 获取地址详情
     * @param id
     * @return
     */
    @ApiOperation(value = "获取收货地址详情", response = AddressDTO.class)
    @GetMapping("/{id}")
    ResultModel<AddressDTO> getAddress(@PathVariable("id") Long id);

    /**
     * 编辑收货地址
     * @param id
     * @param addressDTO
     * @return
     */
    @ApiOperation(value = "编辑收货地址")
    @PutMapping("/{id}")
    ResultModel editAddress(@PathVariable("id") Long id, AddressParamDTO addressDTO);

    /**
     * 删除收货地址
     * @param id
     * @param addressDTO
     * @return
     */
    @ApiOperation(value = "删除收货地址")
    @DeleteMapping("/{id}")
    ResultModel removeAddress(@PathVariable("id") Long id);

}
