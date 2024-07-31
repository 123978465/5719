package com.keyware.MR.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 登录拦截器
 *
 * @author Salmon
 * @date 2022/11/2 17:45
 */
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Cookie[] cookies = request.getCookies();
        //查询当前是否有登录
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                //有登录，则放行
                if ("isLogin".equals(cookie.getName())) {
                    cookie.setMaxAge(30);
                    response.addCookie(cookie);
                    return true;
                }
            }
        }
        //没有登录，跳转到登录页
        response.sendRedirect("/login");
        return false;
    }
}
