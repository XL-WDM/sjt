package com.sjt.business.strategy.sign.mode.impl;

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

        // 3.通过openid or unionid获取授权信息
        List<UserOauths> userOauths = userOauthsMapper.selectOneByOauthIdAndUnionId(openid, unionid);
        Integer maxAge = BaseConstant.Second.DAY;
        if (userOauths == null || userOauths.isEmpty()) {
            // 3-1-1.获取用户
            User user = new User();
            user.setRegisterDate(LocalDateTime.now());
            user.insert();

            // 3-1-2.新增绑定记录
            UserOauths uo = new UserOauths();
            uo.setUserId(user.getId());
            uo.setOauthId(wxAppletSeesionKey.getOpenid());
            uo.setUnionId(wxAppletSeesionKey.getUnionid());
            uo.setSessionKey(wxAppletSeesionKey.getSessionKey());
            uo.setOauthType(DataBaseConstant.OauthType.WX_APPLET.getCode());
            uo.insert();

            return new UserModel(user, maxAge);
        } else {
            // 3-2-1.获取用户信息
            Long userId = userOauths.get(0).getUserId();
            User user = userMapper.selectById(userId);
            CheckObjects.predicate(user.getStatus(),
                    s -> BaseConstant.Status.NO.getCode().equals(s), "用户已冻结");

            for (UserOauths userOauth : userOauths) {
                if (wxAppletSeesionKey.getOpenid().equals(userOauth.getOauthId())) {
                    boolean isChange = false;
                    // 3-2-2.添加unionid
                    if (StringUtils.isEmpty(userOauth.getUnionId())
                            && !StringUtils.isEmpty(wxAppletSeesionKey.getUnionid())) {
                        userOauth.setUnionId(wxAppletSeesionKey.getUnionid());
                        isChange = true;
                    }
                    // 3-2-3.更新sessionKey
                    if (!wxAppletSeesionKey.getSessionKey().equals(userOauth.getSessionKey())) {
                        userOauth.setSessionKey(wxAppletSeesionKey.getSessionKey());
                        isChange = true;
                    }
                    if (isChange) {
                        userOauth.updateById();
                    }

                    return new UserModel(user, maxAge);
                }
            }

            // 3-2-2.新增绑定记录
            UserOauths uo = new UserOauths();
            uo.setUserId(user.getId());
            uo.setOauthId(wxAppletSeesionKey.getOpenid());
            uo.setUnionId(wxAppletSeesionKey.getUnionid());
            uo.setSessionKey(wxAppletSeesionKey.getSessionKey());
            uo.setOauthType(DataBaseConstant.OauthType.WX_APPLET.getCode());
            uo.insert();

            return new UserModel(user, maxAge);
        }
    }
}
