package com.sjt.business.service;

import com.sjt.business.api.dto.req.ProdctsParamDTO;
import com.sjt.business.api.dto.req.QueryProductParamDTO;
import com.sjt.business.api.dto.res.*;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
public interface IProductService {

    /**
     * 获取商品分类
     * @param id
     * @return
     */
    ProductCategoryDTO getProductCategory(Long id);

    /**
     * 获取所有商品分类(树形结构)
     * @return
     */
    List<ProductCategoryDTO> getProductCategoryTree();

    /**
     * 获取商品详情
     * @param id
     * @return
     */
    ProductDetailDTO getProductDetail(Long id);

    /**
     * 获取商品详情描述内容
     * @param id
     * @return
     */
    List<ProductDetailDescDTO> getProductDetailDesc(Long id);

    /**
     * 通过多个ID获取商品信息
     * @param ids
     * @return
     */
    List<ShoppingCartDTO> getProductByIds(List<Long> ids);

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

    /**
     * 商品搜索数量
     * @param queryProductParamDTO
     * @return
     */
    Integer queryProductCountByPage(QueryProductParamDTO queryProductParamDTO);

    /**
     * 商品搜索集合
     * @param queryProductParamDTO
     * @return
     */
    List<ProductDetailDTO> queryProductListByPage(QueryProductParamDTO queryProductParamDTO);
}
