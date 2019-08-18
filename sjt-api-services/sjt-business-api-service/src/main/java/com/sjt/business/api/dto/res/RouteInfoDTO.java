package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/8/16
 */
@ApiModel("物流信息")
@Data
public class RouteInfoDTO {

    /**
     * 物流名称
     */
    @ApiModelProperty("物流名称")
    private String shippingName;

    /**
     * 物流单号
     */
    @ApiModelProperty("物流单号")
    private String shippingCode;

    /**
     * 路由信息
     */
    @ApiModelProperty("路由信息")
    private List<SfRouteDTO> routes;
}
