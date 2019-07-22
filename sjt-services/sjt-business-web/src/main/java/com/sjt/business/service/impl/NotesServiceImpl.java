package com.sjt.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sjt.business.api.dto.res.HomePageNotesDTO;
import com.sjt.business.api.dto.res.NotesDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Notes;
import com.sjt.business.mapper.NotesMapper;
import com.sjt.business.service.INotesService;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
@Slf4j
@Service
public class NotesServiceImpl implements INotesService {

    @Autowired
    private NotesMapper notesMapper;

    @Override
    public HomePageNotesDTO getNotes() {
        HomePageNotesDTO homePageNotesDTO = new HomePageNotesDTO();

        // 1.查询山田日记
        List<Notes> notes = notesMapper.selectPage(new Page<Notes>(1, 4),
                new EntityWrapper<Notes>()
                        .eq("status", BaseConstant.Status.YES.getCode())
                        .eq("is_craftsman", BaseConstant.Status.NO.getCode())
                        .orderBy("create_date", false));
        // 1-1. Entity - DTO
        List<NotesDTO> notesDTOS = BeanCopierUtils.copyList(notes, NotesDTO.class, "noteContext");
        homePageNotesDTO.setNotes(notesDTOS);

        // 2.查询匠人精神
        List<Notes> craftsmanNotes = notesMapper.selectPage(new Page<Notes>(1, 4),
                new EntityWrapper<Notes>()
                        .eq("status", BaseConstant.Status.YES.getCode())
                        .eq("is_craftsman", BaseConstant.Status.YES.getCode())
                        .orderBy("create_date", false));
        // 2-1. Entity - DTO
        List<NotesDTO> craftsmanNotesDTOS = BeanCopierUtils.copyList(craftsmanNotes, NotesDTO.class, "noteContext");
        homePageNotesDTO.setCraftsmanNotes(craftsmanNotesDTOS);

        return homePageNotesDTO;
    }

    @Override
    public NotesDTO getNote(Long id) {
        // 1.参数校验
        CheckObjects.isNull(id, "请选择山田日记");

        // 2.查询
        Notes notes = notesMapper.selectById(id);
        CheckObjects.isNull(notes, "日记不存在");

        // 3.Entity -> DTO
        NotesDTO notesDTO = BeanCopierUtils.copyBean(notes, NotesDTO.class);

        return notesDTO;
    }
}
