package com.sjt.business.api.dto.req;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/8/12
 */
@ApiModel("订单管理 Request params")
@Data
public class OrderManageParamDTO extends PageParamDTO {

    /**
     * 订单状态
     */
    private String status;

    /**
     * 开始时间
     */
    private String startDate;

    /**
     * 结束时间
     */
    private String endDate;
}
