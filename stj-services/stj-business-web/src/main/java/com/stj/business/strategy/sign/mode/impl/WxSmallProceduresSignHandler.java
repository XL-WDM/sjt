package com.stj.business.strategy.sign.mode.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.stj.business.api.dto.req.SignParamDTO;
import com.stj.business.constant.DataBaseConstant;
import com.stj.business.entity.User;
import com.stj.business.entity.UserOauths;
import com.stj.business.mapper.UserMapper;
import com.stj.business.mapper.UserOauthsMapper;
import com.stj.business.strategy.sign.mode.SignModeHandler;
import com.stj.common.utils.CheckObjects;
import com.stj.wechat.constant.WechatConstant;
import com.stj.wechat.service.IWxOauthService;
import com.stj.wechat.vo.res.WxAppletSessionKeyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author: yilan.hu
 * @data: 2019/7/10
 */
@Slf4j
@Component
public class WxSmallProceduresSignHandler implements SignModeHandler {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserOauthsMapper userOauthsMapper;

    @Autowired
    private IWxOauthService iWxOauthService;

    @Override
    public User check(SignParamDTO signParamDTO) {
        // 1.参数校验
        String code = signParamDTO.getCode();
        CheckObjects.isEmpty(code, "登录凭证code不能为空");

        // 2.获取SeesionKey和openid
        WxAppletSessionKeyVO wxAppletSeesionKey = iWxOauthService.getWxAppletSeesionKey(code);
        CheckObjects.isNull(wxAppletSeesionKey, "登录凭证校验不通过");

        String openid = wxAppletSeesionKey.getOpenid();
        String unionid = wxAppletSeesionKey.getUnionid();

        // 3.通过openid获取授权信息
        UserOauths userOauths = new UserOauths();
        userOauths.setOauthId(openid);
        userOauths.setOauthType(DataBaseConstant.OauthType.WX_APPLET.getCode());
        userOauths = userOauthsMapper.selectOne(userOauths);
        if (userOauths == null) {
            Long userId = null;
            if (!StringUtils.isEmpty(unionid)) {
                // 3-1.通过unionid获取授权信息
                List<UserOauths> uos = userOauthsMapper.selectList(new EntityWrapper<UserOauths>()
                        .eq("union_id", unionid));
                if (uos != null && !uos.isEmpty()) {
                    userId = uos.get(0).getUserId();
                }
            }

            // 3-2.获取用户
            User user = new User();

            if (userId != null) {
                user.setId(userId);
                user = user.selectById();
            } else {
                user.setRegisterDate(LocalDateTime.now());
                user.insert();
            }

            // 3-3.新增绑定记录
            userOauths = new UserOauths();
            userOauths.setUserId(user.getId());
            userOauths.setOauthId(wxAppletSeesionKey.getOpenid());
            userOauths.setUnionId(wxAppletSeesionKey.getUnionid());
            userOauths.setOauthType(DataBaseConstant.OauthType.WX_APPLET.getCode());
            if (userOauths.insert()) {
                return user;
            }
        } else {
            // 3-4.添加unionid
            if (StringUtils.isEmpty(userOauths.getUnionId())
                    && !StringUtils.isEmpty(wxAppletSeesionKey.getUnionid())) {
                userOauths.setUnionId(wxAppletSeesionKey.getUnionid());
                userOauths.updateById();
            }
        }

        return userMapper.selectById(userOauths.getId());
    }
}
