package com.mall.wx.util;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "wx")
public class ApiUserUtils {

    private static String getCode;

    private static String webAccessTokenhttps;

    private static String appId;

    private static String secret;

    public static String getCode(String appid, String redirectUri, String scope) {
        return String.format(getCode, appid, redirectUri, scope);
    }

    public static String getWebAccess(String code) {
        return String.format(webAccessTokenhttps, appId, secret, code);
    }

    public static String getGetCode() {
        return getCode;
    }

    public void setGetCode(String getCode) {
        ApiUserUtils.getCode = getCode;
    }


    public static String getWebAccessTokenhttps() {
        return webAccessTokenhttps;
    }

    public void setWebAccessTokenhttps(String webAccessTokenhttps) {
        ApiUserUtils.webAccessTokenhttps = webAccessTokenhttps;
    }

    public static String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        ApiUserUtils.appId = appId;
    }

    public static String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        ApiUserUtils.secret = secret;
    }
}

