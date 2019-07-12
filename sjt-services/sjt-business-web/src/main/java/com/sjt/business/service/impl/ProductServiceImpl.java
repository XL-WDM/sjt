package com.sjt.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sjt.business.api.dto.res.ProductCategoryDTO;
import com.sjt.business.api.dto.res.ProductDetailDTO;
import com.sjt.business.api.dto.res.ProductPicDTO;
import com.sjt.business.api.dto.res.ProductPropertiesDTO;
import com.sjt.business.entity.Product;
import com.sjt.business.entity.ProductCategory;
import com.sjt.business.entity.ProductPic;
import com.sjt.business.entity.ProductProperties;
import com.sjt.business.mapper.ProductCategoryMapper;
import com.sjt.business.mapper.ProductMapper;
import com.sjt.business.mapper.ProductPicMapper;
import com.sjt.business.mapper.ProductPropertiesMapper;
import com.sjt.business.service.IProductService;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.utils.BeanCopierUtils;
import com.sjt.common.utils.CheckObjects;
import com.sjt.common.utils.PriceUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<ProductCategoryDTO> getProductCategory() {
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
        // 2-1.DAO -> DTO
        ProductDetailDTO productDetailDTO = BeanCopierUtils.copyBean(product, ProductDetailDTO.class);
        // 2-2.分 -> 元
        productDetailDTO.setPrice(PriceUtils.centToYuan(product.getPrice()));
        productDetailDTO.setDiscountAmount(PriceUtils.centToYuan(product.getDiscountAmount()));

        // 3.查询商品属性
        List<ProductProperties> productProperties = productPropertiesMapper.selectList(
                new EntityWrapper<ProductProperties>()
                .eq("product_id", product.getId())
                .orderBy("sort_num"));
        // 3-1.DAO -> DTO
        List<ProductPropertiesDTO> productPropertiesDTOS = BeanCopierUtils
                .copyList(productProperties, ProductPropertiesDTO.class);
        productDetailDTO.setProperties(productPropertiesDTOS);

        // 4.查询商品图片
        List<ProductPic> productPics = productPicMapper.selectList(new EntityWrapper<ProductPic>()
                .eq("product_id", product.getId())
                .orderBy("sort_num"));
        // 4-1.DAO - DTO
        List<ProductPicDTO> productPicDTOS = BeanCopierUtils.copyList(productPics, ProductPicDTO.class);
        productDetailDTO.setProductPics(productPicDTOS);

        return productDetailDTO;
    }
}
