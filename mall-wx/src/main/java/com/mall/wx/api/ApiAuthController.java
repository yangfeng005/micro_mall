package com.mall.wx.api;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.backstage.common.utils.http.IPUtil;
import com.backstage.core.jwt.JWTUtil;
import com.backstage.core.result.ServiceResultHelper;
import com.mall.shop.entity.customized.WxUserAO;
import com.mall.shop.service.IWxUserService;
import com.mall.wx.annoation.IgnoreAuth;
import com.mall.wx.entity.FullUserInfo;
import com.mall.wx.entity.UserInfo;
import com.mall.wx.util.ApiUserUtils;
import com.mall.wx.util.CharUtil;
import com.mall.wx.util.HttpClientUtil;
import com.mysql.cj.util.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "API登录授权接口")
@RestController
@RequestMapping("/api/auth")
public class ApiAuthController extends ApiBaseController {

    private static Logger LOG = LoggerFactory.getLogger(ApiAuthController.class);

    @Resource
    public IWxUserService wxUserService;

    /**
     * 微信登录
     */
    @ApiOperation(value = "微信登录")
    @IgnoreAuth
    @PostMapping("loginByWx")
    public Object loginByWeixin(HttpServletRequest request) throws Exception {
        JSONObject jsonParam = this.getJsonRequest();
        FullUserInfo fullUserInfo = null;
        String code = "";
        if (!StringUtils.isNullOrEmpty(jsonParam.getString("code"))) {
            code = jsonParam.getString("code");
        }
        if (null != jsonParam.get("userInfo")) {
            fullUserInfo = jsonParam.getObject("userInfo", FullUserInfo.class);
        }
        if (null == fullUserInfo) {
            return ServiceResultHelper.genResultWithFaild("登录失败", -1);
        }

        Map<String, Object> result = new HashMap<>();
        UserInfo userInfo = fullUserInfo.getUserInfo();

        //获取openid
        String requestUrl = ApiUserUtils.getWebAccess(code);//通过自定义工具类组合出小程序需要的登录凭证 code
        LOG.info("》》》组合token为：" + requestUrl);
        JSONObject sessionData = JSON.parseObject(HttpClientUtil.get(requestUrl, "UTF-8"));

        if (null == sessionData || StringUtils.isNullOrEmpty(sessionData.getString("openid"))) {
            return ServiceResultHelper.genResultWithFaild("登录失败", -1);
        }
        //验证用户信息完整性
       /* String sha1 = CharUtil.getSha1(fullUserInfo.getRawData() + sessionData.getString("session_key"));
        if (!fullUserInfo.getSignature().equals(sha1)) {
            return ServiceResultHelper.genResultWithFaild("登录失败", -1);
        }*/
        Date nowTime = new Date();
        WxUserAO wxUser = wxUserService.queryByOpenId(sessionData.getString("openid")).getData();
        if (null == wxUser) {
            wxUser = new WxUserAO();
            wxUser.setUsername("微信用户" + CharUtil.getRandomString(12));
            wxUser.setPassword(sessionData.getString("openid"));
            wxUser.setRegisterTime(nowTime);
            wxUser.setRegisterIp(IPUtil.getClientIp(request));
            wxUser.setLastLoginIp(IPUtil.getClientIp(request));
            wxUser.setLastLoginTime(nowTime);
            wxUser.setWeixinOpenid(sessionData.getString("openid"));
            wxUser.setAvatar(userInfo.getAvatarUrl());
            //性别 0：未知、1：男、2：女
            wxUser.setGender(userInfo.getGender());
            wxUser.setNickname(userInfo.getNickName());
            wxUserService.insert(wxUser);
        } else {
            wxUser.setLastLoginIp(IPUtil.getClientIp(request));
            wxUser.setLastLoginTime(nowTime);
            wxUserService.update(wxUser);
        }

        Map<String, Object> map = new HashMap();
        map.put("userName", wxUser.getUsername());
        map.put("userId", wxUser.getId());
        result.put("token", JWTUtil.sign(map));
        result.put("userInfo", userInfo);
        result.put("userId", wxUser.getId());
        return ServiceResultHelper.genResultWithSuccess(result);
    }

}