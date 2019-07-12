package com.sjt.business.service;

import com.sjt.business.api.dto.res.ProductDetailDTO;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
public interface IProductService {

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    ProductDetailDTO getProductDetail(Long id);
}
