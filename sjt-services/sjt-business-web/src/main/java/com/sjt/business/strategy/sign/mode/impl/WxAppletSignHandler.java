package com.sjt.business.strategy.sign.mode.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.sjt.business.api.dto.req.SignParamDTO;
import com.sjt.business.constant.DataBaseConstant;
import com.sjt.business.entity.User;
import com.sjt.business.entity.UserOauths;
import com.sjt.business.mapper.UserMapper;
import com.sjt.business.mapper.UserOauthsMapper;
import com.sjt.business.strategy.sign.mode.SignModeHandler;
import com.sjt.common.base.constant.BaseConstant;
import com.sjt.common.utils.CheckObjects;
import com.sjt.wechat.api.dto.res.WxAppletSessionKeyDTO;
import com.sjt.wechat.service.IWxOauthService;
import com.sjt.wechat.vo.res.WxAppletSessionKeyVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 微信小程序登陆
 * @author: yilan.hu
 * @data: 2019/7/10
 */
@Slf4j
@Service
public class WxAppletSignHandler implements SignModeHandler {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserOauthsMapper userOauthsMapper;

    @Autowired
    private IWxOauthService iWxOauthService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public UserModel check(SignParamDTO signParamDTO) {
        // 1.参数校验
        String code = signParamDTO.getCode();
        CheckObjects.isEmpty(code, "登录凭证code不能为空");

        // 2.获取SeesionKey和openid
        WxAppletSessionKeyDTO wxAppletSeesionKey = iWxOauthService.getWxAppletSeesionKey(code);
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
                        .eq("union_id", unionid)
                        .eq("status", BaseConstant.Status.YES.getCode()));
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
            userOauths.setSessionKey(wxAppletSeesionKey.getSessionKey());
            userOauths.setOauthType(DataBaseConstant.OauthType.WX_APPLET.getCode());
            userOauths.insert();
        } else {
            boolean isChange = false;
            // 3-4.添加unionid
            if (StringUtils.isEmpty(userOauths.getUnionId())
                    && !StringUtils.isEmpty(wxAppletSeesionKey.getUnionid())) {
                userOauths.setUnionId(wxAppletSeesionKey.getUnionid());
                isChange = true;
            }
            // 3-5.更新sessionKey
            if (!wxAppletSeesionKey.getSessionKey().equals(userOauths.getSessionKey())) {
                userOauths.setSessionKey(wxAppletSeesionKey.getSessionKey());
                isChange = true;
            }

            if (isChange) {
                userOauths.updateById();
            }
        }

        User user = userMapper.selectById(userOauths.getUserId());

        return new UserModel(user, BaseConstant.Second.DAY);
    }
}
