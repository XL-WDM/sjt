package com.sjt.business.service;

import com.sjt.business.api.dto.req.NotesParamDTO;
import com.sjt.business.api.dto.res.HomePageNotesDTO;
import com.sjt.business.api.dto.res.NoteDTO;
import com.sjt.business.api.dto.res.NotesDTO;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
public interface INotesService {

    /**
     * 获取山田日记列表
     * @return
     */
    HomePageNotesDTO getNotes();

    /**
     * 分页获取山田日记列表
     * @param notesParamDTO
     * @return
     */
    Integer getNoteCountByPage(NotesParamDTO notesParamDTO);

    /**
     * 分页获取山田日记列表
     * @param notesParamDTO
     * @return
     */
    NotesDTO getNotesByPage(NotesParamDTO notesParamDTO);

    /**
     * 查询山田日记
     * @param id
     * @return
     */
    NoteDTO getNote(Long id);
}
