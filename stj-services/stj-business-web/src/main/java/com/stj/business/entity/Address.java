package com.stj.business.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author: yilan.hu
 * @data: 2019/7/9
 */
@Data
@TableName("t_address")
public class Address extends Model<Address> implements Serializable {
    private static final long serialVersionUID = 388418228909266457L;

    /**
     * 自增ID
     */
    @TableId
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 联系人
     */
    private String contacts;

    /**
     * 联系电话
     */
    private String phone;

    /**
     * 城市
     */
    private String city;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 门牌号
     */
    private String doorNumber;

    /**
     * 地址标签(1-家, 2-公司, 3-学校)
     */
    private String tag;

    /**
     * 称呼(1-先生, 2-女士)
     */
    private String call;

    /**
     * 是否默认地址(0-不是, 1-是)
     */
    private String isDefault;

    /**
     * 状态(0-无效, 1-有效)
     */
    private String status;

    @Override
    protected Serializable pkVal() {
        return id;
    }
}
