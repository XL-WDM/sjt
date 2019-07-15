package com.sjt.business.api.dto.res;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
@ApiModel("首页山田日记")
@Data
public class HomePageNotesDTO {

    /**
     * 首页山田日记
     */
    @ApiModelProperty("首页山田日记")
    private List<NotesDTO> notes;

    /**
     * 首页匠人精神
     */
    @ApiModelProperty("首页匠人精神")
    private List<NotesDTO> craftsmanNotes;
}
