package com.sjt.business.service;

import com.sjt.business.api.dto.res.HomePageNotesDTO;

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
}
