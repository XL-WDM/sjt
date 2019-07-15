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
@TableName("t_banner")
public class Banner extends Model<Banner> implements Serializable {
    private static final long serialVersionUID = 994127101160747525L;

    /**
     * 自增id
     */
    @TableId
    private Long id;

    /**
     * banner名称
     */
    private String bannerName;

    /**
     * banner类型(1-首页top轮播图, 2-GIF小视频, 3-山田日记banner, 4-首页center轮播图)
     */
    private String bannerType;

    /**
     * 图片url地址
     */
    private String imgUrl;

    /**
     * banner跳转地址
     */
    private String url;

    /**
     * 地址类型(1-内部地址, 2-外部地址)
     */
    private String urlType;

    /**
     * 状态(0-无效 1-有效)
     */
    private String status;

    /**
     * 排序编号
     */
    private Integer sortNum;

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
