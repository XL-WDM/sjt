package com.sjt.business.config.sf;

import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/8/9
 */
@Data
public class SfLogistics {

    /**
     * 顾客编码
     */
    private String clientCode;

    /**
     * 校验码
     */
    private String checkword;

    /**
     * 地址
     */
    private String url;

    /**
     * 路由查询xml
     */
    private String routeXml;
}
