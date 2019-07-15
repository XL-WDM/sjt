package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
@ApiModel("山田日记列表信息")
@Data
public class NotesDTO {

    /**
     * 日记编号
     */
    @ApiModelProperty("日记编号")
    private Long id;

    /**
     * 日记标题
     */
    @ApiModelProperty("日记标题")
    private String title;

    /**
     * 日记大图
     */
    @ApiModelProperty("日记大图")
    private String bigImg;

    /**
     * 日记小图
     */
    @ApiModelProperty("日记小图")
    private String smallImg;

    /**
     * 日记内容
     */
    @ApiModelProperty("日记内容")
    private String noteContext;
}
