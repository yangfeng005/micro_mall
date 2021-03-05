package com.mall.wx.interceptor;

import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.backstage.core.jwt.JWTUtil;
import com.mall.shop.entity.customized.WxUserAO;
import com.mall.shop.service.IWxUserService;
import com.mall.wx.annoation.IgnoreAuth;
import com.mall.wx.exception.ApiRRException;
import com.mall.wx.util.TokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 权限(Token)验证
 */
@Component
public class AuthorizationInterceptor implements HandlerInterceptor {


    @Resource
    private IWxUserService wxUserService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        //支持跨域请求
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,X-Nideshop-Token,X-URL-PATH,content-type");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));

        IgnoreAuth annotation;
        if (handler instanceof HandlerMethod) {
            annotation = ((HandlerMethod) handler).getMethodAnnotation(IgnoreAuth.class);
        } else {
            return true;
        }

        //如果有@IgnoreAuth注解，则不验证token
        if (annotation != null) {
            return true;
        }

        //获取token
        String token = TokenUtil.getToken(request);
        //从token中获取用户id
        String userId = TokenUtil.getUserId(token);

        if (StringUtils.isEmpty(userId)) {
            throw new ApiRRException("token错误!");
        } else {
            WxUserAO user = wxUserService.selectByPrimaryKey(userId).getData();
            if (user == null) {
                throw new ApiRRException("用户不存在!");
            } else {
                try {
                    JWTUtil.verify(token, "userId", userId);
                } catch (TokenExpiredException var6) {
                    throw new ApiRRException("token已过期!");
                } catch (SignatureVerificationException var7) {
                    throw new ApiRRException("密码不正确!");
                } catch (Exception e) {
                    throw new ApiRRException("token认证失败!");
                }
            }
        }
        return true;
    }

    //方法执行后
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {

    }

    //页面渲染前
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

    }

}
