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
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Api(tags = "API登录授权接口")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static Logger LOG = LoggerFactory.getLogger(AuthController.class);

    @Resource
    public IWxUserService wxUserService;

    /**
     * 微信登录
     */
    @ApiOperation(value = "微信登录")
    @IgnoreAuth
    @PostMapping("loginByWx")
    public Object loginByWeixin(HttpServletRequest request) throws Exception {
        JSONObject jsonParam = this.getJsonRequest(request);
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

        Map<String, Object> resultObj = new HashMap<>();
        UserInfo userInfo = fullUserInfo.getUserInfo();

        //获取openid
        String requestUrl = ApiUserUtils.getWebAccess(code);//通过自定义工具类组合出小程序需要的登录凭证 code
        LOG.info("》》》组合token为：" + requestUrl);
        JSONObject sessionData = JSON.parseObject(HttpClientUtil.get(requestUrl, "UTF-8"));

        if (null == sessionData || StringUtils.isNullOrEmpty(sessionData.getString("openid"))) {
            return ServiceResultHelper.genResultWithFaild("登录失败", -1);
        }
        //验证用户信息完整性
        String sha1 = CharUtil.getSha1(fullUserInfo.getRawData() + sessionData.getString("session_key"));
        if (!fullUserInfo.getSignature().equals(sha1)) {
            return ServiceResultHelper.genResultWithFaild("登录失败", -1);
        }
        Date nowTime = new Date();
        WxUserAO userVo = wxUserService.queryByOpenId(sessionData.getString("openid")).getData();
        if (null == userVo) {
            userVo = new WxUserAO();
            userVo.setUsername("微信用户" + CharUtil.getRandomString(12));
            userVo.setPassword(sessionData.getString("openid"));
            userVo.setRegisterTime(nowTime);
            userVo.setRegisterIp(IPUtil.getClientIp(request));
            userVo.setLastLoginIp(IPUtil.getClientIp(request));
            userVo.setLastLoginTime(nowTime);
            userVo.setWeixinOpenid(sessionData.getString("openid"));
            userVo.setAvatar(userInfo.getAvatarUrl());
            //性别 0：未知、1：男、2：女
            userVo.setGender(userInfo.getGender());
            userVo.setNickname(userInfo.getNickName());
            wxUserService.insert(userVo);
        } else {
            userVo.setLastLoginIp(IPUtil.getClientIp(request));
            userVo.setLastLoginTime(nowTime);
            wxUserService.update(userVo);
        }

        Map<String, Object> map = new HashMap();
        resultObj.put("token", JWTUtil.sign(map));
        resultObj.put("userInfo", userInfo);
        resultObj.put("userId", userVo.getId());
        return ServiceResultHelper.genResultWithSuccess(resultObj);
    }

    public JSONObject getJsonRequest(HttpServletRequest request) {
        JSONObject result = null;
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            char[] buff = new char[1024];
            int len;
            while ((len = reader.read(buff)) != -1) {
                sb.append(buff, 0, len);
            }
            result = JSONObject.parseObject(sb.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}