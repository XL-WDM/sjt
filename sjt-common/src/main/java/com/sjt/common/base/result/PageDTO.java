package com.sjt.common.base.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/17
 */
@ApiModel("分页响应数据")
@Data
public class PageDTO {

    /**
     * 数据总数
     */
    @ApiModelProperty("数据总数")
    private Integer total;

    /**
     * 分页数据
     */
    @ApiModelProperty("分页数据")
    private List rows;

    public PageDTO() {

    }

    public PageDTO(Integer total, List rows) {
        this.total = total;
        this.rows = rows;
    }
}
