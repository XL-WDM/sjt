package com.sjt.business.service.impl;

import com.sf.csim.express.service.CallExpressServiceTools;
import com.sf.csim.express.service.VerifyCodeUtil;
import com.sjt.business.service.IShunFengService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: yilan.hu
 * @data: 2019/8/9
 */
@Service
public class ShunFengServiceImpl implements IShunFengService {

    static {
        CallExpressServiceTools.getInstance();
    }

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void routeQuery() {
        String reqXml = "";
        try {
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("shunfeng/5.route_queryByMailNo.txt");
            byte[] bs = new byte[is.available()];
            is.read(bs);
            reqXml = new String(bs);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // .设置物流单号
        reqXml = reqXml.replace("TRACKING_NUMBER", "123123123123123");

        String url = "https://bsp-oisp.sf-express.com/bsp-oisp/sfexpressService";

        String clientCode = "WKWLKJ";
        String myReqXML = reqXml.replace("CLIENT_CODE", clientCode);

        String checkword = "6ObwuMJqkvdSqx5VpdIHDQYmRbGoObv6";
        String verifyCode = getVerifyCode(myReqXML, checkword);

        System.out.println("请求报文：" + myReqXML);
        String respXml = CallExpressServiceTools.callSfExpressServiceByCSIM(url, myReqXML, clientCode, checkword);
        System.out.println("响应报文：" + respXml);
    }

    private String getVerifyCode(String reqXML, String checkword) {
        return VerifyCodeUtil.md5EncryptAndBase64(reqXML + checkword);
    }
}
