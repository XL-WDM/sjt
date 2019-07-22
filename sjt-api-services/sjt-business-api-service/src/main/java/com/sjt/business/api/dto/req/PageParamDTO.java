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
    @ApiModelProperty(value = "页号", example = "0")
    private Integer pageNo;

    /**
     * 页大小
     */
    @ApiModelProperty(value = "页大小", example = "0")
    private Integer pageSize;
}
