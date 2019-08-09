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

        //
        String reqXml = sfLogistics.getRouteXml().replace("TRACKING_NUMBER", orderNo);
        String clientCode = sfLogistics.getClientCode();
        String checkword = sfLogistics.getCheckword();
        String myReqXML = reqXml.replace("CLIENT_CODE", clientCode);
        String url = sfLogistics.getUrl();
        String verifyCode = getVerifyCode(myReqXML, checkword);

        System.out.println("请求报文：" + myReqXML);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(url, myReqXML, clientCode, checkword);
        System.out.println("响应报文：" + respXml);

        return reqXml;
    }

    private String getVerifyCode(String reqXML, String checkword) {
        return VerifyCodeUtil.md5EncryptAndBase64(reqXML + checkword);
    }
}
