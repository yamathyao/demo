package com.example.demo.login;

import com.example.demo.validate.model.ResultVO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yao
 * @date 2020/1/20
 */
@RestController
@RequestMapping("/user")
public class UserAuthController {

    private static final int TOKEN_EXPIRE_SECONDS = 60 * 60;

    private static final String COOKIE_NAME = "FL";

    @RequestMapping("/login")
    public ResultVO login(HttpServletResponse response) {
        // 这里的cookie值我随便写了一个字符串"token"
        // 生产环境中token有一套复杂的生成规则，例如MD5(用户名+登陆ip+当前时间)后的字符串
        // 登录的时候我们先根据输入的用户名拿到用户信息
        // 然后把 token->用户信息 的映射关系放到redis中，或者mysql中
        Cookie cookie = new Cookie(COOKIE_NAME, "token");
        // 设置cookie的过期时间为1个小时，即1个小时内你都不用重新登陆
        cookie.setMaxAge(TOKEN_EXPIRE_SECONDS);
        response.addCookie(cookie);
        return ResultVO.success("success");
    }

    @RequestMapping("/cart")
    public ResultVO cart() {
        return ResultVO.success("success");
    }
}
