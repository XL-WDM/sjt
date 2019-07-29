package com.sjt.business.api.impl;

import com.sjt.business.api.dto.req.ProdctsParamDTO;
import com.sjt.business.api.dto.req.QueryProductParamDTO;
import com.sjt.business.api.dto.res.CategoryProductsDTO;
import com.sjt.business.api.dto.res.ProductCategoryDTO;
import com.sjt.business.api.dto.res.ProductDetailDTO;
import com.sjt.business.api.expose.ProductApi;
import com.sjt.business.service.IProductService;
import com.sjt.common.base.constant.ResultConstant;
import com.sjt.common.base.result.ResultDTO;
import com.sjt.common.utils.CheckObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@RestController
public class ProductApiService implements ProductApi {

    @Autowired
    private IProductService iProductService;

    @Override
    public ResultDTO<ProductDetailDTO> getProductDetail(Long id) {
        ProductDetailDTO productDetail = iProductService.getProductDetail(id);
        return ResultDTO.data(productDetail);
    }

    @Override
    public ResultDTO<List<ProductDetailDTO>> getProductDetails(@RequestBody List<Long> ids) {
        List<ProductDetailDTO> productDetailDTOS = iProductService.getProductByIds(ids);
        return ResultDTO.data(productDetailDTOS);
    }

    @Override
    public ResultDTO<CategoryProductsDTO> getCategoryProductList() {
        List<CategoryProductsDTO> categoryProductList = iProductService.getCategoryProductList();
        return ResultDTO.data(categoryProductList);
    }

    @Override
    public ResultDTO getProductList(ProdctsParamDTO prodctsParamDTO) {
        // 获取商品分类信息
        CheckObjects.isNull(prodctsParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        ProductCategoryDTO productCategory = iProductService.getProductCategory(prodctsParamDTO.getCategoryId());
        CheckObjects.isNull(productCategory, "商品分类不存在");

        // 获取商品信息
        List<ProductDetailDTO> rows = iProductService.getPageProductList(prodctsParamDTO);

        // 获取商品总数
        Integer total = iProductService.getPageProductCount(prodctsParamDTO);
        return ResultDTO.page(total, rows, productCategory.getImgUrl());
    }

    @Override
    public ResultDTO queryProductList(QueryProductParamDTO queryProductParamDTO) {
        List<ProductDetailDTO> rows = iProductService.queryProductListByPage(queryProductParamDTO);
        Integer total = iProductService.queryProductCountByPage(queryProductParamDTO);

        return ResultDTO.page(total, rows);
    }
}
