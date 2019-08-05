package com.sjt.business.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.sjt.business.entity.Product;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
public interface ProductMapper extends BaseMapper<Product> {

    /**
     * 查询销量
     * @param productId
     * @return
     */
    Integer selectSoldOut(Long productSpecId);
}
