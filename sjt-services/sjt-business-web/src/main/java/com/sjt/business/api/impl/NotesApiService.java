package com.sjt.business.api.impl;

import com.sjt.business.api.dto.req.NotesParamDTO;
import com.sjt.business.api.dto.res.NoteDTO;
import com.sjt.business.api.dto.res.NotesDTO;
import com.sjt.business.api.expose.NotesApi;
import com.sjt.business.service.INotesService;
import com.sjt.common.base.result.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yilan.hu
 * @data: 2019/7/22
 */
@RestController
public class NotesApiService implements NotesApi {

    @Autowired
    private INotesService iNotesService;

    @Override
    public ResultDTO<NoteDTO> getNote(Long id) {
        NoteDTO note = iNotesService.getNote(id);
        return ResultDTO.data(note);
    }

    @Override
    public ResultDTO getNotes(NotesParamDTO notesParamDTO) {
        NotesDTO notesDTO = iNotesService.getNotesByPage(notesParamDTO);
        Integer total = iNotesService.getNoteCountByPage(notesParamDTO);
        return ResultDTO.page(total, notesDTO.getNotes(), notesDTO.getBanner());
    }
}
