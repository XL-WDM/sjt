package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/17
 */
@ApiModel("分页 Request params")
@Data
public class PageParamDTO {

    /**
     * 页号
     */
    @ApiModelProperty("页号")
    private Integer pageNo;

    /**
     * 页大小
     */
    @ApiModelProperty("页大小")
    private Integer pageSize;
}
