package com.sjt.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author: yilan.hu
 * @data: 2019/7/15
 */
@Data
@TableName("t_notes")
public class Notes extends Model<Notes> implements Serializable {
    private static final long serialVersionUID = -7324531867913189856L;

    /**
     * 自增id
     */
    @TableId
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 日记描述
     */
    private String descript;

    /**
     * 大图
     */
    private String bigImg;

    /**
     * 小图
     */
    private String smallImg;

    /**
     * 内容
     */
    private String noteContext;

    /**
     * 是否为匠人精神日记(0-否, 1-是)
     */
    private String isCraftsman;

    /**
     * 状态(0-可用, 1-不可用)
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
