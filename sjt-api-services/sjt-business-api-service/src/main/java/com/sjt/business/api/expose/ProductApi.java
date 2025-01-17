package com.sjt.business.api.expose;

import com.sjt.business.api.dto.req.ProdctsParamDTO;
import com.sjt.business.api.dto.req.QueryProductParamDTO;
import com.sjt.business.api.dto.res.CategoryProductsDTO;
import com.sjt.business.api.dto.res.ProductDetailDescDTO;
import com.sjt.business.api.dto.res.ProductDetailDTO;
import com.sjt.business.api.dto.res.ShoppingCartDTO;
import com.sjt.common.base.result.ResultDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@Api(description = "商品信息", tags = "商品信息")
@RequestMapping("/product")
public interface ProductApi {

    /**
     * 获取商品详情信息
     * @param id 商品id
     * @return
     */
    @ApiImplicitParam(name = "id", value = "商品编号", required = true)
    @ApiOperation(value = "获取商品详情信息", response = ProductDetailDTO.class)
    @GetMapping("/open-api/detail")
    ResultDTO<ProductDetailDTO> getProductDetail(Long id);

    /**
     * 获取商品详情描述内容
     * @param id 商品id
     * @return
     */
    @ApiImplicitParam(name = "id", value = "商品编号", required = true)
    @ApiOperation(value = "获取商品详情描述内容", response = ProductDetailDescDTO.class)
    @GetMapping("/open-api/detail-desc")
    ResultDTO<List<ProductDetailDescDTO>> getProductDetailDesc(Long id);

    /**
     * 获取多个商品详情信息
     * @param ids 商品id数组
     * @return
     */
    @ApiOperation(value = "获取多个商品详情信息", response = ShoppingCartDTO.class)
    @PostMapping("/open-api/details")
    ResultDTO<List<ShoppingCartDTO>> getProductDetails(List<Long> ids);

    /**
     * 获取商品分类列表
     * @param prodctsParamDTO
     * @return
     */
    @ApiOperation(value = "获取分类商品列表", response = CategoryProductsDTO.class)
    @GetMapping("/open-api/list")
    ResultDTO<CategoryProductsDTO> getCategoryProductList();

    /**
     * 获取商品列表
     * @param prodctsParamDTO
     * @return
     */
    @ApiOperation(value = "获取商品列表", response = ProductDetailDTO.class)
    @GetMapping("/open-api/list/page")
    ResultDTO getProductList(ProdctsParamDTO prodctsParamDTO);

    /**
     * 商品搜索
     * @param queryProductParamDTO
     * @return
     */
    @ApiOperation(value = "商品搜索", response = ProductDetailDTO.class)
    @GetMapping("/open-api/query/list/page")
    ResultDTO queryProductList(QueryProductParamDTO queryProductParamDTO);
}
