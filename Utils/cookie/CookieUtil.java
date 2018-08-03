package com.waston.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * @Author wangtao
 * Created on 2018/1/31
 **/
public class CookieUtil {

    private static final String COOKIE_NAME = "loginCookie";

    /**
     * tomcat7：以.开头 tomcat8:不需要.
     * 如mall.com,  则此cookie对于www.mall.com, admin.mall.com可见
     * 而www.admin.mall.com不可见, 即同级可见
     */
    private static final String DOMAIN = "mall.com";

    /**
     * cookie的有效期, 30天
     */
    private static final int MAX_AGE = 60 * 60 * 24 * 30;

    /**
     * 获取登录cookie
     * @param request
     */
    private static Cookie getCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if(cookies != null) {
            for(Cookie cookie : cookies)
                if(Objects.equals(cookie.getName(), COOKIE_NAME))
                    return cookie;
        }
        return null;
    }

    /**
     * 获取登录cookie的值
     * @param request
     * @return
     */
    public static String getSessionKey(HttpServletRequest request) {
        Cookie cookie = getCookie(request);
        return cookie == null ? null : cookie.getValue();
    }

    /**
     * 回写登录cookie
     * @param value redis存取用户信息的key
     * @param response
     */
    public static void addCookie(String value, HttpServletResponse response) {
        Cookie cookie = new Cookie(COOKIE_NAME, value);
        cookie.setDomain(DOMAIN);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        //单位为秒, 负数表示不存储, 0表示删除此cookie
        cookie.setMaxAge(MAX_AGE);
        response.addCookie(cookie);
    }

    /**
     * 删除登录cookie, 删除cookie时一定要设置domain, path等属性
     * @param request
     */
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookie(request);
        if(cookie != null) {
            cookie.setDomain(DOMAIN);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }

    }

}
