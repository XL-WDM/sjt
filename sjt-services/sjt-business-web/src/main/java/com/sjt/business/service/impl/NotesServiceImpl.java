package com.sjt.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sjt.business.api.dto.req.NotesParamDTO;
import com.sjt.business.api.dto.res.HomePageNotesDTO;
import com.sjt.business.api.dto.res.NoteDTO;
import com.sjt.business.api.dto.res.NotesDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.Banner;
import com.sjt.business.entity.Notes;
import com.sjt.business.mapper.BannerMapper;
import com.sjt.business.mapper.NotesMapper;
import com.sjt.business.service.INotesService;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.base.constant.ResultConstant;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Autowired
    private BannerMapper bannerMapper;

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
        List<NoteDTO> notesDTOS = BeanCopierUtils.copyList(notes, NoteDTO.class, "noteContext");
        homePageNotesDTO.setNotes(notesDTOS);

        // 2.查询匠人精神
        List<Notes> craftsmanNotes = notesMapper.selectPage(new Page<Notes>(1, 4),
                new EntityWrapper<Notes>()
                        .eq("status", BaseConstant.Status.YES.getCode())
                        .eq("is_craftsman", BaseConstant.Status.YES.getCode())
                        .orderBy("create_date", false));
        // 2-1. Entity - DTO
        List<NoteDTO> craftsmanNotesDTOS = BeanCopierUtils.copyList(craftsmanNotes, NoteDTO.class, "noteContext");
        homePageNotesDTO.setCraftsmanNotes(craftsmanNotesDTOS);

        return homePageNotesDTO;
    }

    @Override
    public Integer getNoteCountByPage(NotesParamDTO notesParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(notesParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);

        // 2.查询
        EntityWrapper<Notes> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("status", BaseConstant.Status.YES.getCode());
        String noteKeyWord = notesParamDTO.getNoteKeyWord();
        if (noteKeyWord != null && !"".equals(noteKeyWord.trim())) {
            noteKeyWord = noteKeyWord.trim();
            entityWrapper.andNew().like("title", noteKeyWord).or().like("descript", noteKeyWord);
        }
        Integer total = notesMapper.selectCount(entityWrapper);

        return total;
    }

    @Override
    public NotesDTO getNotesByPage(NotesParamDTO notesParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(notesParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        Integer pageNo = notesParamDTO.getPageNo();
        Integer pageSize = notesParamDTO.getPageSize();
        CheckObjects.isPage(pageNo, pageSize);

        // 2.查询banner
        List<Banner> banners = bannerMapper.selectPage(new Page<Banner>(1, 1), new EntityWrapper<Banner>()
                .eq("banner_type", DataBaseConstant.BannerType.ST_NOTES_BANNER.getCode())
                .eq("status", BaseConstant.Status.YES.getCode())
                .orderBy("create_date", false));
        String banner = banners != null && !banners.isEmpty() ? banners.get(0).getImgUrl() : "";

        // 3.查询日记列表
        EntityWrapper<Notes> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("status", BaseConstant.Status.YES.getCode());
        String noteKeyWord = notesParamDTO.getNoteKeyWord();
        if (noteKeyWord != null && !"".equals(noteKeyWord.trim())) {
            noteKeyWord = noteKeyWord.trim();
            entityWrapper.andNew().like("title", noteKeyWord).or().like("descript", noteKeyWord);
        }
        List<Notes> notes = notesMapper.selectPage(new Page<Notes>(pageNo, pageSize),
                entityWrapper.orderBy("create_date", false));

        // 4.Entity -> DTO
        List<NoteDTO> noteDTOS = BeanCopierUtils.copyList(notes, NoteDTO.class);

        // 5.结果封装
        NotesDTO notesDTO = new NotesDTO();
        notesDTO.setBanner(banner);
        notesDTO.setNotes(noteDTOS);

        return notesDTO;
    }

    @Override
    public NoteDTO getNote(Long id) {
        // 1.参数校验
        CheckObjects.isNull(id, "请选择山田日记");

        // 2.查询
        Notes notes = notesMapper.selectById(id);
        CheckObjects.isNull(notes, "日记不存在");

        // 3.Entity -> DTO
        NoteDTO notesDTO = BeanCopierUtils.copyBean(notes, NoteDTO.class);

        return notesDTO;
    }
}
