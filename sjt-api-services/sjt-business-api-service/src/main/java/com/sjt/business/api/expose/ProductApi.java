package com.sjt.business.api.expose;

import com.sjt.business.api.dto.req.ProdctsParamDTO;
import com.sjt.business.api.dto.res.ProductDetailDTO;
import com.sjt.business.api.dto.res.ProductsDTO;
import com.sjt.common.base.result.PageDTO;
import com.sjt.common.base.result.ResultDTO;
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
    @GetMapping("/open-api/detail")
    ResultDTO<ProductDetailDTO> getProductDetail(Long id);

    /**
     * 获取商品分类列表
     * @param prodctsParamDTO
     * @return
     */
    @ApiOperation(value = "获取商品分类列表", response = ProductsDTO.class)
    @GetMapping("/open-api/list")
    ResultDTO<PageDTO> getCategoryProductList(ProdctsParamDTO prodctsParamDTO);
}
