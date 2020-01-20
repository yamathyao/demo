package com.example.demo.login;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author yao
 * @date 2020/1/20
 */

public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        Cookie[] cookies = request.getCookies();
        if (cookies == null || cookies.length == 0) {
            this.processError(response);
            return false;
        }
        // 这里cookie的名字在UserAuthController里面定义了
        String cookieName = "FL";
        String token = null;
        for (Cookie cookie : cookies) {
            if (cookieName.equals(cookie.getName())) {
                token = cookie.getValue();
                break;
            }
        }
        if (token == null) {
            this.processError(response);
            return false;
        }
        // 在登录的时候我们设置了 token-> 用户信息 的映射关系
        // 根据token从redis或者mysql中拿到用户信息，然后设置到requst请求中，供后续请求使用
        // 分布式session就这样实现了
        request.setAttribute("userInfo", null);
        return true;
    }

    private void processError(HttpServletResponse response) throws IOException {
        response.reset();
        response.setContentType("application/json;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("请登录");
    }
}
