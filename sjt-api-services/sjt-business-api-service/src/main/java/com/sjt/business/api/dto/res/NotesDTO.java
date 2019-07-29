package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/29
 */
@ApiModel("山田日记列表")
@Data
public class NotesDTO {

    /**
     * banner
     */
    @ApiModelProperty("banner")
    private String banner;

    /**
     * 日记列表
     */
    @ApiModelProperty("日记列表")
    private List<NoteDTO> notes;
}
