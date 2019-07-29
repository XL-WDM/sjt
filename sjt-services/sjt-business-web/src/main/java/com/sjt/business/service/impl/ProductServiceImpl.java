package com.sjt.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.sjt.business.api.dto.req.ProdctsParamDTO;
import com.sjt.business.api.dto.req.QueryProductParamDTO;
import com.sjt.business.api.dto.res.*;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.*;
import com.sjt.business.mapper.*;
import com.sjt.business.service.IProductService;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.base.constant.ResultConstant;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import com.sjt.common.utils.PriceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPropertiesMapper productPropertiesMapper;

    @Autowired
    private ProductPicMapper productPicMapper;

    @Autowired
    private ProductStockMapper productStockMapper;

    @Override
    public ProductCategoryDTO getProductCategory(Long id) {
        // 1.参数校验
        CheckObjects.isNull(id, "请选择商品分类");

        // 2.查询
        ProductCategory productCategory = productCategoryMapper.selectById(id);

        // Entity -> DTO
        return BeanCopierUtils.copyBean(productCategory, ProductCategoryDTO.class);
    }

    @Override
    public List<ProductCategoryDTO> getProductCategoryTree() {
        // 查询所有商品分类信息
        List<ProductCategory> productCategories = productCategoryMapper.selectList(new EntityWrapper<ProductCategory>()
                .eq("status", BaseConstant.Status.YES.getCode()));

        // 递归处理
        return recursionProductCategory(productCategories, null);
    }

    private List<ProductCategoryDTO> recursionProductCategory(final List<ProductCategory> categorys, Long pid) {
        List<ProductCategoryDTO> productCategorys = new ArrayList();
        if (categorys != null && !categorys.isEmpty()) {
            for (ProductCategory category : categorys) {
                boolean flag = false;

                if (pid == null) {
                    if (category.getPid() == null) {
                        flag = true;
                    }
                } else if (pid.equals(category.getPid()))  {
                    flag = true;
                }

                if (flag) {
                    ProductCategoryDTO productCategoryDTO = BeanCopierUtils.copyBean(category, ProductCategoryDTO.class);
                    List<ProductCategoryDTO> productCategoryDTOS = recursionProductCategory(categorys, category.getId());
                    productCategoryDTO.setChildren(productCategoryDTOS);
                    productCategorys.add(productCategoryDTO);
                }
            }
        }

        return productCategorys;
    }

    @Override
    public ProductDetailDTO getProductDetail(Long id) {
        // 1.参数校验
        CheckObjects.isNull(id, "商品编号不能为空");

        // 2.查询商品
        Product product = productMapper.selectById(id);
        CheckObjects.isNull(product, "商品不存在");
        CheckObjects.predicate(product.getPublishStatus(),
                s -> DataBaseConstant.ProductPushStatus.INVALID.getCode().equals(s), "商品不存在");

        // 2-1.分 -> 元
        product.setPrice(PriceUtils.centToYuan(product.getPrice()));
        product.setDiscountAmount(PriceUtils.centToYuan(product.getDiscountAmount()));

        // 2-2.Entity -> DTO
        ProductDetailDTO productDetailDTO = BeanCopierUtils.copyBean(product, ProductDetailDTO.class);

        // 3.查询商品属性
        List<ProductProperties> productProperties = productPropertiesMapper.selectList(
                new EntityWrapper<ProductProperties>()
                .eq("product_id", product.getId())
                .orderBy("sort_num"));
        // 3-1.Entity -> DTO
        List<ProductPropertiesDTO> productPropertiesDTOS = BeanCopierUtils
                .copyList(productProperties, ProductPropertiesDTO.class);
        productDetailDTO.setProperties(productPropertiesDTOS);

        // 4.查询商品图片
        List<ProductPic> productPics = productPicMapper.selectList(new EntityWrapper<ProductPic>()
                .eq("product_id", product.getId())
                .orderBy("sort_num"));
        // 4-1.Entity - DTO
        List<ProductPicDTO> productPicDTOS = BeanCopierUtils.copyList(productPics, ProductPicDTO.class);
        productDetailDTO.setProductPics(productPicDTOS);

        // 5.查询库存
        ProductStock productStock = productStockMapper.selectByProductId(product.getId());
        Integer stock = productStock == null ? 0 : productStock.getProductStockNum();
        productDetailDTO.setStock(stock);

        // 6.查询销量
        Integer soldOut = productMapper.selectSoldOut(product.getId());
        productDetailDTO.setSoldOut(soldOut);

        return productDetailDTO;
    }

    @Override
    public List<ProductDetailDTO> getProductByIds(List<Long> ids) {
        // 1.参数验证处理
        if (ids == null || ids.isEmpty()) {
            return new ArrayList<>();
        }

        // 2.查询
        List<Product> products = productMapper.selectList(new EntityWrapper<Product>().in("id", ids));

        // 3.Entity -> DTO
        List<ProductDetailDTO> productDetailDTOS = products.stream().map(product -> {
            ProductDetailDTO productDetailDTO = BeanCopierUtils.copyBean(product, ProductDetailDTO.class);
            productDetailDTO.setPrice(PriceUtils.centToYuan(product.getPrice()));
            productDetailDTO.setDiscountAmount(PriceUtils.centToYuan(product.getDiscountAmount()));
            return productDetailDTO;
        }).collect(Collectors.toList());

        return productDetailDTOS;
    }

    @Override
    public List<ProductDetailDTO> getNewArrivals() {
        // 1.查询新品推荐产品
        List<Product> products = productMapper.selectList(new EntityWrapper<Product>()
                .eq("publish_status", DataBaseConstant.ProductPushStatus.UPPER_SHELF.getCode())
                .eq("new_arrivals", BaseConstant.Status.YES.getCode())
                .orderBy("create_date", false));

        List<ProductDetailDTO> newArrivals = products.stream().map(p -> {
            // 2.Entity -> DTO
            ProductDetailDTO productDetailDTO = BeanCopierUtils.copyBean(p, ProductDetailDTO.class);
            // 3.分 -> 元
            productDetailDTO.setPrice(PriceUtils.centToYuan(p.getPrice()));
            productDetailDTO.setDiscountAmount(PriceUtils.centToYuan(p.getDiscountAmount()));
            // 4.查询库存
            ProductStock productStock = productStockMapper.selectByProductId(p.getId());
            Integer stock = productStock == null ? 0 : productStock.getProductStockNum();
            productDetailDTO.setStock(stock);
            // 5.查询销量
            Integer soldOut = productMapper.selectSoldOut(p.getId());
            productDetailDTO.setSoldOut(soldOut);

            return productDetailDTO;
        }).collect(Collectors.toList());

        return newArrivals;
    }

    @Override
    public List<CategoryProductsDTO> getCategoryProductList() {
        // 1.获取商品分类信息
        List<ProductCategory> productCategories = productCategoryMapper.selectList(
                new EntityWrapper<ProductCategory>()
                        .eq("category_level", DataBaseConstant.ProductCategoryLevel.Category_Level_THREE.getCode())
                        .orderBy("pid")
                        .orderBy("create_date", false));

        if (productCategories == null || productCategories.isEmpty()) {
            return new ArrayList<>();
        }

        List<CategoryProductsDTO> productsDTOS = new ArrayList<>();

        // 2.获取分类下的商品(4个)
        for (ProductCategory productCategory : productCategories) {
            List<Product> products = productMapper.selectPage(new Page<Product>(1, 4),
                    new EntityWrapper<Product>()
                            .eq("three_level_category", productCategory.getId())
                            .eq("publish_status", DataBaseConstant.ProductPushStatus.UPPER_SHELF.getCode())
                            .orderBy("create_date", false));

            List<ProductDetailDTO> productDetailDTOS = products.stream().map(p -> {
                // 3.Entity -> DTO
                ProductDetailDTO productDetailDTO = BeanCopierUtils.copyBean(p, ProductDetailDTO.class);
                // 4.分 -> 元
                productDetailDTO.setPrice(PriceUtils.centToYuan(p.getPrice()));
                productDetailDTO.setDiscountAmount(PriceUtils.centToYuan(p.getDiscountAmount()));
                // 5.查询库存
                ProductStock productStock = productStockMapper.selectByProductId(p.getId());
                Integer stock = productStock == null ? 0 : productStock.getProductStockNum();
                productDetailDTO.setStock(stock);
                // 6.查询销量
                Integer soldOut = productMapper.selectSoldOut(p.getId());
                productDetailDTO.setSoldOut(soldOut);

                return productDetailDTO;
            }).collect(Collectors.toList());

            CategoryProductsDTO productsDTO = new CategoryProductsDTO();
            productsDTO.setCategoryId(productCategory.getId());
            productsDTO.setCategoryImg(productCategory.getImgUrl());
            productsDTO.setProducts(productDetailDTOS);

            productsDTOS.add(productsDTO);
        }

        return productsDTOS;
    }

    @Override
    public Integer getPageProductCount(ProdctsParamDTO prodctsParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(prodctsParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        Long categoryId = prodctsParamDTO.getCategoryId();
        CheckObjects.isNull(categoryId, "请选择商品分类");

        // 2.查询分类
        ProductCategory productCategory = productCategoryMapper.selectById(categoryId);
        CheckObjects.isNull(productCategory, "商品分类不存在");

        // 3.查询商品
        Integer total = productMapper.selectCount(new EntityWrapper<Product>()
                .eq("one_level_category", categoryId)
                .or()
                .eq("two_level_category", categoryId)
                .or()
                .eq("three_level_category", categoryId)
                .andNew()
                .eq("publish_status", DataBaseConstant.ProductPushStatus.UPPER_SHELF.getCode()));

        return total;
    }

    @Override
    public List<ProductDetailDTO> getPageProductList(ProdctsParamDTO prodctsParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(prodctsParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        Long categoryId = prodctsParamDTO.getCategoryId();
        CheckObjects.isNull(categoryId, "请选择商品分类");
        Integer pageNo = prodctsParamDTO.getPageNo();
        Integer pageSize = prodctsParamDTO.getPageSize();
        CheckObjects.isPage(pageNo, pageSize);

        // 2.查询分类
        ProductCategory productCategory = productCategoryMapper.selectById(categoryId);
        CheckObjects.isNull(productCategory, "商品分类不存在");

        // 3.查询商品
        List<Product> products = productMapper.selectPage(new Page<Product>(pageNo, pageSize),
                new EntityWrapper<Product>()
                        .eq("one_level_category", categoryId)
                        .or()
                        .eq("two_level_category", categoryId)
                        .or()
                        .eq("three_level_category", categoryId)
                        .andNew()
                        .eq("publish_status", DataBaseConstant.ProductPushStatus.UPPER_SHELF.getCode())
                        .orderBy("create_date", false));

        List<ProductDetailDTO> productDetailDTOS = products.stream().map(p -> {
            // 4.Entity -> DTO
            ProductDetailDTO productDetailDTO = BeanCopierUtils.copyBean(p, ProductDetailDTO.class);
            // 5.分 -> 元
            productDetailDTO.setPrice(PriceUtils.centToYuan(p.getPrice()));
            productDetailDTO.setDiscountAmount(PriceUtils.centToYuan(p.getDiscountAmount()));
            // 6.查询库存
            ProductStock productStock = productStockMapper.selectByProductId(p.getId());
            Integer stock = productStock == null ? 0 : productStock.getProductStockNum();
            productDetailDTO.setStock(stock);
            // 7.查询销量
            Integer soldOut = productMapper.selectSoldOut(p.getId());
            productDetailDTO.setSoldOut(soldOut);

            return productDetailDTO;
        }).collect(Collectors.toList());

        return productDetailDTOS;
    }

    @Override
    public Integer queryProductCountByPage(QueryProductParamDTO queryProductParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(queryProductParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);

        // 3.查询商品数量
        EntityWrapper<Product> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("publish_status", DataBaseConstant.ProductPushStatus.UPPER_SHELF.getCode());

        String productKeyWord = queryProductParamDTO.getProductKeyWord();
        if (productKeyWord != null && !"".equals(productKeyWord.trim())) {
            entityWrapper.andNew()
                    .like("product_name", productKeyWord)
                    .or()
                    .like("descript", productKeyWord);
        }

        Integer total = productMapper.selectCount(entityWrapper);

        return total;
    }

    @Override
    public List<ProductDetailDTO> queryProductListByPage(QueryProductParamDTO queryProductParamDTO) {
        // 1.参数校验
        CheckObjects.isNull(queryProductParamDTO, ResultConstant.PARAMETERS_CANNOT_BE_NULL);
        Integer pageNo = queryProductParamDTO.getPageNo();
        Integer pageSize = queryProductParamDTO.getPageSize();
        CheckObjects.isPage(pageNo, pageSize);

        // 3.查询商品数量
        EntityWrapper<Product> entityWrapper = new EntityWrapper<>();
        entityWrapper.eq("publish_status", DataBaseConstant.ProductPushStatus.UPPER_SHELF.getCode());

        String productKeyWord = queryProductParamDTO.getProductKeyWord();
        if (productKeyWord != null && !"".equals(productKeyWord.trim())) {
            entityWrapper.andNew()
                    .like("product_name", productKeyWord)
                    .or()
                    .like("descript", productKeyWord);
        }

        List<Product> products = productMapper.selectPage(new Page<Product>(pageNo, pageSize), entityWrapper.orderBy("create_date", false));

        // Entity -> DTO
        List<ProductDetailDTO> productDetailDTOS = BeanCopierUtils.copyList(products, ProductDetailDTO.class);

        return productDetailDTOS;
    }
}
