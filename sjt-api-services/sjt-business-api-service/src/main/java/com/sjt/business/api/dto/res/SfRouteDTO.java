package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/8/15
 */
@ApiModel("顺丰路由信息")
@Data
public class SfRouteDTO {

    /**
     * 路由节点具体描述
     */
    @ApiModelProperty("路由节点具体描述")
    private String remark;

    /**
     * 路由节点发生的时间
     */
    @ApiModelProperty("路由节点发生的时间")
    private String acceptTime;

    /**
     * 路由节点发生的地点
     */
    @ApiModelProperty("路由节点发生的地点")
    private String acceptAddress;

    /**
     * 路由节点操作码
     */
    @ApiModelProperty("路由节点操作码")
    private String opcode;
}
