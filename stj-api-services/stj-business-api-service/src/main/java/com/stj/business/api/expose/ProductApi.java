package com.stj.business.api.expose;

import com.stj.business.api.dto.res.ProductDetailDTO;
import com.stj.common.base.result.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@Api(description = "商品信息")
@RequestMapping("/product")
public interface ProductApi {

    /**
     * 商品详情信息
     * @param id 商品id
     * @return
     */
    @ApiOperation(value = "商品详情信息", response = ProductDetailDTO.class)
    @GetMapping("/detail")
    ResultModel<ProductDetailDTO> getProductDetail(Long id);
}
