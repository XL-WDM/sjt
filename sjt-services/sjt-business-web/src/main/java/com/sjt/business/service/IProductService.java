package com.sjt.business.service;

import com.sjt.business.api.dto.req.ProdctsParamDTO;
import com.sjt.business.api.dto.res.CategoryProductsDTO;
import com.sjt.business.api.dto.res.ProductCategoryDTO;
import com.sjt.business.api.dto.res.ProductDetailDTO;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
public interface IProductService {

    /**
     * 获取商品分类
     *
     * @return
     */
    List<ProductCategoryDTO> getProductCategory();

    /**
     * 获取商品详情
     *
     * @param id
     * @return
     */
    ProductDetailDTO getProductDetail(Long id);

    /**
     * 获取新品推荐
     * @return
     */
    List<ProductDetailDTO> getNewArrivals();

    /**
     * 获取商品分类列表
     * @param prodctsParamDTO
     * @return
     */
    List<CategoryProductsDTO> getCategoryProductList();

    /**
     * 分页获取商品总数
     * @param prodctsParamDTO
     * @return
     */
    Integer getPageProductCount(ProdctsParamDTO prodctsParamDTO);

    /**
     * 分页获取商品信息
     * @param prodctsParamDTO
     * @return
     */
    List<ProductDetailDTO> getPageProductList(ProdctsParamDTO prodctsParamDTO);
}
