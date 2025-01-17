package com.sjt.business.api.impl;

import com.sjt.business.api.dto.req.ProdctsParamDTO;
import com.sjt.business.api.dto.req.QueryProductParamDTO;
import com.sjt.business.api.dto.res.*;
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
    public ResultDTO<List<ProductDetailDescDTO>> getProductDetailDesc(Long id) {
        List<ProductDetailDescDTO> productDetailDesc = iProductService.getProductDetailDesc(id);

        return ResultDTO.data(productDetailDesc);
    }

    @Override
    public ResultDTO<List<ShoppingCartDTO>> getProductDetails(@RequestBody List<Long> ids) {
        List<ShoppingCartDTO> productByIds = iProductService.getProductByIds(ids);

        return ResultDTO.data(productByIds);
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
        String categoryImgUrl = null;
        if (prodctsParamDTO.getCategoryId() != null) {
            ProductCategoryDTO productCategory = iProductService.getProductCategory(prodctsParamDTO.getCategoryId());
            if (productCategory != null) {
                categoryImgUrl = productCategory.getImgUrl();
            }
        }

        // 获取商品信息
        List<ProductDetailDTO> rows = iProductService.getPageProductList(prodctsParamDTO);

        // 获取商品总数
        Integer total = iProductService.getPageProductCount(prodctsParamDTO);
        return ResultDTO.page(total, rows, categoryImgUrl);
    }

    @Override
    public ResultDTO queryProductList(QueryProductParamDTO queryProductParamDTO) {
        List<ProductDetailDTO> rows = iProductService.queryProductListByPage(queryProductParamDTO);
        Integer total = iProductService.queryProductCountByPage(queryProductParamDTO);

        return ResultDTO.page(total, rows);
    }
}
