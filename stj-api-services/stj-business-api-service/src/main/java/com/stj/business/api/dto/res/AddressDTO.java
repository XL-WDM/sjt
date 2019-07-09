package com.stj.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@ApiModel("收货地址信息")
@Data
public class AddressDTO {
    /**
     * 自增ID
     */
    @ApiModelProperty("地址编号id(查询、更新、删除时需提供)")
    private Long id;

    /**
     * 联系人
     */
    @ApiModelProperty("联系人")
    private String contacts;

    /**
     * 联系电话
     */
    @ApiModelProperty("联系电话")
    private String phone;

    /**
     * 城市
     */
    @ApiModelProperty("城市")
    private String city;

    /**
     * 收货地址
     */
    @ApiModelProperty("收货地址")
    private String address;

    /**
     * 门牌号
     */
    @ApiModelProperty("门牌号")
    private String doorNumber;

    /**
     * 地址标签(1-家, 2-公司, 3-学校)
     */
    @ApiModelProperty("地址标签(1-家, 2-公司, 3-学校)")
    private String tag;

    /**
     * 称呼(1-先生, 2-女士)
     */
    @ApiModelProperty("称呼(1-先生, 2-女士)")
    private String contactsCall;

    /**
     * 是否默认地址(0-不是, 1-是)
     */
    @ApiModelProperty("是否默认地址(0-不是, 1-是)")
    private String isDefault;
}
