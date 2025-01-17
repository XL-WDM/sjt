package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@ApiModel("收货地址信息 Request params")
@Data
public class AddressParamDTO {

    /**
     * 收货地址编号
     */
    @ApiModelProperty(value = "收货地址编号")
    private Long id;

    /**
     * 联系人
     */
    @ApiModelProperty(value = "联系人", required = true)
    private String contacts;

    /**
     * 联系电话
     */
    @ApiModelProperty(value = "联系电话", required = true)
    private String phone;

    /**
     * 省
     */
    @ApiModelProperty(value = "省", required = true)
    private String province;

    /**
     * 市
     */
    @ApiModelProperty(value = "市", required = true)
    private String city;

    /**
     * 县
     */
    @ApiModelProperty(value = "县", required = true)
    private String county;

    /**
     * 收货地址
     */
    @ApiModelProperty(value = "收货地址", required = true)
    private String address;

    /**
     * 门牌号
     */
    @ApiModelProperty(value = "门牌号", required = true)
    private String doorNumber;

    /**
     * 是否默认地址(0-不是, 1-是)
     */
    @ApiModelProperty(value = "是否默认地址(0-不是, 1-是)", required = true)
    private String isDefault;

    /**
     * 地址标签(1-家, 2-公司, 3-学校)
    @ApiModelProperty(value = "地址标签(1-家, 2-公司, 3-学校)", required = true)
    private String tag;
     */

    /**
     * 称呼(1-先生, 2-女士)
    @ApiModelProperty(value = "称呼(1-先生, 2-女士)", required = true)
    private String contactsCall;
     */
}
