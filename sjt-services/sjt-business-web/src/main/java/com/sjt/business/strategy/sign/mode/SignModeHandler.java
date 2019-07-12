package com.sjt.business.strategy.sign.mode;

import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.entity.User;
import lombok.Data;

/**
 * @author: yilan.hu
 * @data: 2019/7/8
 */
public interface SignModeHandler {

    /**
     * 验证
     * @param signParamDTO
     * @return
     */
    UserModel check(SignParamDTO signParamDTO);

    @Data
    class UserModel {

        public UserModel() {}

        public UserModel(User user, Integer maxAge) {
            this.user = user;
            this.maxAge = maxAge;
        }

        private User user;

        private Integer maxAge;
    }
}
