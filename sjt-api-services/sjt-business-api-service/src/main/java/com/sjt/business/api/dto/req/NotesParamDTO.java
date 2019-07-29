package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/29
 */
@ApiModel("日记查询 Request params")
@Data
public class NotesParamDTO extends PageParamDTO {

    /**
     * 搜索关键字
     */
    @ApiModelProperty("搜索关键字")
    private String noteKeyWord;
}
