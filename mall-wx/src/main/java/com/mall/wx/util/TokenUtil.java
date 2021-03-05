package com.mall.wx.util;

import com.backstage.core.jwt.JWTUtil;
import com.mall.wx.exception.ApiRRException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * token工具
 */
public class TokenUtil {

    public static final String LOGIN_TOKEN_KEY = "X-Nideshop-Token";


    /**
     * 获取token
     *
     * @param request
     * @return
     */
    public static String getToken(HttpServletRequest request) {
        //从header中获取token
        String token = request.getHeader(LOGIN_TOKEN_KEY);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = request.getParameter(LOGIN_TOKEN_KEY);
        }
        return token;
    }

    /**
     * 获取用户id
     *
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        //token为空
        if (StringUtils.isBlank(token)) {
            throw new ApiRRException("请先登录", 401);
        }

        return JWTUtil.getFieldValue(token, "userId");
    }

}
