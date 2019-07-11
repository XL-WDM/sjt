package com.stj.business.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stj.business.api.dto.res.ProductDetailDTO;
import com.stj.business.api.dto.res.ProductPicDTO;
import com.stj.business.api.dto.res.ProductPropertiesDTO;
import com.stj.business.constant.DataBaseConstant;
import com.stj.business.entity.Product;
import com.stj.business.entity.ProductPic;
import com.stj.business.entity.ProductProperties;
import com.stj.business.mapper.ProductMapper;
import com.stj.business.mapper.ProductPicMapper;
import com.stj.business.mapper.ProductPropertiesMapper;
import com.stj.business.service.IProductService;
import com.stj.common.base.constant.BaseConstant;
import com.stj.common.utils.BeanCopierUtils;
import com.stj.common.utils.CheckObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: yilan.hu
 * @data: 2019/7/11
 */
@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductPropertiesMapper productPropertiesMapper;

    @Autowired
    private ProductPicMapper productPicMapper;

    @Override
    public ProductDetailDTO getProductDetail(Long id) {
        // 1.参数校验
        CheckObjects.isNull(id, "商品编号不能为空");

        // 2.查询商品
        Product product = productMapper.selectById(id);
        CheckObjects.isNull(product, "商品不存在");
        // 2-1.DAO -> DTO
        ProductDetailDTO productDetailDTO = BeanCopierUtils.copyBean(product, ProductDetailDTO.class);

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
        // 4-1.获取主图
        List<ProductPicDTO> masterPic = productPics.stream()
                .filter(pic -> BaseConstant.Status.YES.getCode().equals(pic.getIsMaster()))
                .map(pic -> BeanCopierUtils.copyBean(pic, ProductPicDTO.class))
                .collect(Collectors.toList());
        productDetailDTO.setMasterPic(masterPic);
        // 4-2.获取详情图
        List<ProductPicDTO> slavePic = productPics.stream()
                .filter(pic -> BaseConstant.Status.NO.getCode().equals(pic.getIsMaster()))
                .map(pic -> BeanCopierUtils.copyBean(pic, ProductPicDTO.class))
                .collect(Collectors.toList());
        productDetailDTO.setSlavePic(slavePic);

        return productDetailDTO;
    }
}
