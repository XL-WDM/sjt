package com.sjt.business.api.expose;

import com.sjt.business.api.dto.req.NotesParamDTO;
import com.sjt.business.api.dto.res.NoteDTO;
import com.sjt.business.api.dto.res.NotesDTO;
import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yilan.hu
 * @data: 2019/7/22
 */
@Api(description = "山田日记", tags = "山田日记")
@RequestMapping("/notes")
public interface NotesApi {

    /**
     * 查询山田日记
     * @param id
     * @return
     */
    @ApiImplicitParam(name = "id", value = "日记编号", required = true)
    @ApiOperation(value = "查询山田日记", response = NoteDTO.class)
    @GetMapping("/open-api/view")
    ResultDTO<NoteDTO> getNote(Long id);

    /**
     * 日记列表
     * @param notesParamDTO
     * @return
     */
    @ApiOperation(value = "日记列表", response = NoteDTO.class)
    @GetMapping("/open-api/list/page")
    ResultDTO getNotes(NotesParamDTO notesParamDTO);
}
