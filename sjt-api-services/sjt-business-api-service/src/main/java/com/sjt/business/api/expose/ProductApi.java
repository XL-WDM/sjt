package com.sjt.business.api.expose;

import com.sjt.business.api.dto.res.ProductCategoryDTO;
import com.sjt.business.api.dto.res.ProductDetailDTO;
import com.sjt.common.base.result.ResultModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@Api(description = "商品信息")
@RequestMapping("/product")
public interface ProductApi {

    /**
     * 获取商品分类信息
     * @return
     */
    @ApiOperation(value = "获取商品分类信息", response = ProductCategoryDTO.class)
    @GetMapping("/open-api/category/list")
    ResultModel<List<ProductCategoryDTO>> getProductcategorys();

    /**
     * 商品详情信息
     * @param id 商品id
     * @return
     */
    @ApiOperation(value = "商品详情信息", response = ProductDetailDTO.class)
    @GetMapping("/open-api/detail")
    ResultModel<ProductDetailDTO> getProductDetail(Long id);
}
