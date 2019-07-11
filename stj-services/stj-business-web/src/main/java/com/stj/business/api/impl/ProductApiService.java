package com.stj.business.api.impl;

import com.stj.business.api.dto.res.ProductDetailDTO;
import com.stj.business.api.expose.ProductApi;
import com.stj.business.service.IProductService;
import com.stj.common.base.result.ResultModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@RestController
public class ProductApiService implements ProductApi {

    @Autowired
    private IProductService iProductService;

    @Override
    public ResultModel<ProductDetailDTO> getProductDetail(Long id) {
        ProductDetailDTO productDetail = iProductService.getProductDetail(id);
        return ResultModel.data(productDetail);
    }
}
