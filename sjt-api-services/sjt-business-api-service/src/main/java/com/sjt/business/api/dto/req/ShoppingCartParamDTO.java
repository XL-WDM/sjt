package com.sjt.business.api.dto.req;

import lombok.Data;

import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/30
 */
@Data
public class ShoppingCartParamDTO {

    List<Long> ids;
}
