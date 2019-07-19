package com.sjt.business.api.impl;

import com.sjt.business.api.dto.req.ProdctsParamDTO;
import com.sjt.business.api.dto.res.CategoryProductsDTO;
import com.sjt.business.api.dto.res.ProductDetailDTO;
import com.sjt.business.api.expose.ProductApi;
import com.sjt.business.service.IProductService;
import com.sjt.common.base.result.ResultDTO;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResultDTO<CategoryProductsDTO> getCategoryProductList() {
        List<CategoryProductsDTO> categoryProductList = iProductService.getCategoryProductList();
        return ResultDTO.data(categoryProductList);
    }

    @Override
    public ResultDTO getProductList(ProdctsParamDTO prodctsParamDTO) {
        List<ProductDetailDTO> rows = iProductService.getPageProductList(prodctsParamDTO);
        Integer total = iProductService.getPageProductCount(prodctsParamDTO);
        return ResultDTO.page(total, rows);
    }
}
