package com.sjt.business.config.sf;

import com.sf.csim.express.service.CallExpressServiceTools;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author: yilan.hu
 * @data: 2019/8/9
 */
@Configuration
@PropertySource("classpath:sf-logistics.yml")
public class SfLogisticsConfiguration {

    static {
        CallExpressServiceTools.getInstance();
    }

    @Bean
    public SfLogistics sfLogistics(@Value("${clientCode}") String clientCode,
                                   @Value("${checkword}") String checkword,
                                   @Value("${url}") String url,
                                   @Value("${routeXml}") String routeXml
    ) {
        SfLogistics sfLogistics = new SfLogistics();
        sfLogistics.setClientCode(clientCode);
        sfLogistics.setCheckword(checkword);
        sfLogistics.setUrl(url);
        sfLogistics.setRouteXml(routeXml);

        return sfLogistics;
    }
}
