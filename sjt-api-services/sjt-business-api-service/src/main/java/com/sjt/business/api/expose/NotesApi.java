package com.sjt.business.api.expose;

import com.sjt.business.api.dto.res.NotesDTO;
import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yilan.hu
 * @data: 2019/7/22
 */
@Api(description = "山田日记")
@RequestMapping("/notes")
public interface NotesApi {

    /**
     * 查询山田日记
     * @param id
     * @return
     */
    @ApiOperation(value = "查询山田日记", response = NotesDTO.class)
    @GetMapping("/open-api/view")
    ResultDTO<NotesDTO> getNote(Long id);
}
