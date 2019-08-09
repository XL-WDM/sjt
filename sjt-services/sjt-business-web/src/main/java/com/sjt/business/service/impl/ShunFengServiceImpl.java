package com.sjt.business.service.impl;

import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.csim.express.service.VerifyCodeUtil;
import com.sjt.business.config.sf.SfLogistics;
import com.sjt.business.service.IShunFengService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author: yilan.hu
 * @data: 2019/8/9
 */
@Slf4j
@Service
public class ShunFengServiceImpl implements IShunFengService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SfLogistics sfLogistics;

    @Override
    public Object routeQuery(String orderNo) {

        // 获取配置信息
        String reqXml = sfLogistics.getRouteXml();
        String clientCode = sfLogistics.getClientCode();
        String checkword = sfLogistics.getCheckword();
        String url = sfLogistics.getUrl();

        // 处理报文
        reqXml = reqXml.replace("CLIENT_CODE", clientCode)
                       .replace("TRACKING_TYPE", "1")
                       .replace("NUMBER", orderNo);
        String verifyCode = getVerifyCode(reqXml , checkword);

        log.info("【顺丰物流查询】 request -> {}", reqXml);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(url, reqXml, clientCode, checkword);
        log.info("【顺丰物流查询】 result -> {}", respXml);

        return respXml;
    }

    private String getVerifyCode(String reqXML, String checkword) {
        return VerifyCodeUtil.md5EncryptAndBase64(reqXML + checkword);
    }
}
