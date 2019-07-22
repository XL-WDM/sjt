package com.sjt.business.service;

import com.sjt.business.api.dto.res.HomePageNotesDTO;
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
     * 查询山田日记
     * @param id
     * @return
     */
    NotesDTO getNote(Long id);
}
