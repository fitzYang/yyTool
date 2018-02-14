/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.servlet.cookie;

import com.google.common.collect.Maps;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class Cookies {

    // cookie 默认时间, 1周
    public static int defaultCookieLife = 60 * 60 * 24 * 7;

    public static void addCookie(HttpServletResponse response, String name, String value) {
        addCookie(response, name, value, defaultCookieLife, false);
    }

    public static void addCookieByHttpOnly(HttpServletResponse response,
                                           String name, String value) {

        addCookie(response, name, value, defaultCookieLife, true);
    }

    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        addCookie(response, name, value, maxAge, false);
    }


    /**
     * 删除自动登录保存的信息
     *
     * @param request  http request
     * @param name     要清除的COOKIE值
     * @param response 响应
     */
    public static void deleteCookie(HttpServletRequest request, HttpServletResponse response, String
            name) {
        Cookie cookie = getCookieByName(request, name);
        if (null == cookie) {
            return;
        }
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }

    public static void addCookie(
            HttpServletResponse response,
            String name, String value,
            int maxAge, boolean httpOnly) {
        Cookie cookie = new Cookie(name, value);
        cookie.setPath("/");
        cookie.setHttpOnly(httpOnly);
        // 如果把有效周期设置为0，则表示此Cookie对象存放在浏览器后将立即失效，
        // 如果把有效周期设置为任意一个负数，则当浏览器关闭后，此Cookie对象立即失效。
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public static Cookie getCookieByName(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = readCookieMap(request);
        if (cookieMap.containsKey(name)) {
            return cookieMap.get(name);
        } else {
            return null;
        }
    }

    public static String getCookieValue(HttpServletRequest request, String name) {
        String cookieValue = "";
        Cookie c = getCookieByName(request, name);

        if (null != c) {
            cookieValue = c.getValue();
        }

        return cookieValue;
    }

    public static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = Maps.newHashMap();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }
}
