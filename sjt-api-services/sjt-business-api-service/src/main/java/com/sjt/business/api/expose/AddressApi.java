package com.sjt.business.api.expose;

import com.sjt.business.api.dto.req.AddressParamDTO;
import com.sjt.business.api.dto.res.AddressDTO;
import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Api(description = "收货地址")
@RequestMapping("/address")
public interface AddressApi {

    /**
     * 获取收货地址
     * @return
     */
    @ApiOperation(value = "获取收货地址列表", response = AddressDTO.class)
    @GetMapping("/list")
    ResultDTO<List<AddressDTO>> getAddressList();

    /**
     * 新增收货地址
     * @param addressDTO
     * @return
     */
    @ApiOperation(value = "新增收货地址")
    @PostMapping("/add")
    ResultDTO createAddress(@RequestBody AddressParamDTO addressDTO);

    /**
     * 获取地址详情
     * @param id
     * @return
     */
    @ApiOperation(value = "获取收货地址详情", response = AddressDTO.class)
    @GetMapping("/detail")
    ResultDTO<AddressDTO> getAddress(Long id);

    /**
     * 编辑收货地址
     * @param id
     * @param addressDTO
     * @return
     */
    @ApiOperation(value = "编辑收货地址")
    @PostMapping("/edit")
    ResultDTO editAddress(@RequestBody AddressParamDTO addressDTO);

    /**
     * 删除收货地址
     * @param id
     * @param addressDTO
     * @return
     */
    @ApiOperation(value = "删除收货地址")
    @PostMapping("/remove")
    ResultDTO removeAddress(@RequestBody Long id);

}
