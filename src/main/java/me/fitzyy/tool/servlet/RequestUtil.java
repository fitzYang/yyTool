/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.servlet;

import com.google.common.base.Strings;
import com.google.common.net.HttpHeaders;

import org.apache.commons.lang3.StringUtils;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import me.fitzyy.tool.StringPool;
import me.fitzyy.tool.constants.MediaTypes;


/**
 * <p> request工具类</p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class RequestUtil {


    private static final String BLANK_UNKNOWN = " unknown";
    private static final String UNKNOWN       = "unknown";

    /**
     * 获取请求basePath
     */
    @SuppressWarnings("AlibabaUndefineMagicConstant")
    public static String getBasePath(HttpServletRequest request) {
        StringBuilder basePath = new StringBuilder();
        String scheme = request.getScheme();
        String domain = request.getServerName();
        int port = request.getServerPort();
        basePath.append(scheme);
        basePath.append("://");
        basePath.append(domain);
        if ("http".equalsIgnoreCase(scheme) && 80 != port) {
            basePath.append(":").append(String.valueOf(port));
        } else if ("https".equalsIgnoreCase(scheme) && port != 443) {
            basePath.append(":").append(String.valueOf(port));
        }
        return basePath.toString();
    }

    /**
     * 获取ip工具类，除了getRemoteAddr，其他ip均可伪造
     */
    public static String getIpAddr(HttpServletRequest request) {
        // 网宿cdn的真实ip
        String ip = request.getHeader("Cdn-Src-Ip");

        if (ip == null || ip.length() == 0 || BLANK_UNKNOWN.equalsIgnoreCase(ip)) {
            // 蓝讯cdn的真实ip
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || BLANK_UNKNOWN.equalsIgnoreCase(ip)) {
            // 获取代理ip
            ip = request.getHeader("X-Forwarded-For");
        }
        if (ip == null || ip.length() == 0 || BLANK_UNKNOWN.equalsIgnoreCase(ip)) {
            // 获取代理ip
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            // 获取代理ip
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            // 获取真实ip
            ip = request.getRemoteAddr();
        }
        return ip;
    }

    /**
     * 移除request指定参数
     */
    public static String removeParam(HttpServletRequest request, String paramName) {
        StringBuilder queryString = new StringBuilder();
        Enumeration keys = request.getParameterNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            if (key.equals(paramName)) {
                continue;
            }
            if (Strings.isNullOrEmpty(queryString.toString())) {
                queryString = new StringBuilder(key + StringPool.EQUALS + request.getParameter(key));
            } else {
                queryString.append(StringPool.AMPERSAND).append(key)
                        .append(StringPool.EQUALS).append(request.getParameter(key));
            }
        }
        return queryString.toString();
    }


    /**
     * 判断请求是否是ajax请求
     *
     * @param request http请求
     * @return 是否为Ajax请求
     */
    public static boolean isAjax(HttpServletRequest request) {
        final String requestedWithHeader = request.getHeader(HttpHeaders.X_REQUESTED_WITH);
        final String acceptHeader = request.getHeader(HttpHeaders.ACCEPT);
        return (StringUtils.isNotEmpty(acceptHeader) && acceptHeader.contains(MediaTypes.JSON))
                || (StringUtils.isNotEmpty(requestedWithHeader) && requestedWithHeader.contains("XMLHttpRequest"));
    }

    /**
     * 是否是Pjax请求数据: https://github.com/defunkt/jquery-pjax
     *
     * @param request Request
     * @return true 为 Pjax请求
     */
    public static boolean isPjaxRequest(HttpServletRequest request) {
        return StringUtils.isNotEmpty(request.getHeader("X-PJAX"));
    }

    /**
     * 是否是Flash请求数据
     *
     * @param request Request
     * @return true Flash请求数据
     */
    public static boolean isFlashRequest(HttpServletRequest request) {
        return "Shockwave Flash".equals(request.getHeader("User-Agent")) || StringUtils.isNotEmpty(request.getHeader("x-flash-version"));
    }


    /**
     * 判断是否为搜索引擎
     *
     * @param request Request
     * @return true 表示为搜索引擎的访问
     */
    public static boolean isRobot(HttpServletRequest request) {
        String ua = request.getHeader(HttpHeaders.USER_AGENT);
        return !StringUtils.isBlank(ua)
                && (ua.contains("Baiduspider")
                || ua.contains("Googlebot")
                || ua.contains("sogou")
                || ua.contains("sina")
                || ua.contains("iaskspider")
                || ua.contains("ia_archiver")
                || ua.contains("Sosospider")
                || ua.contains("YoudaoBot")
                || ua.contains("yahoo")
                || ua.contains("yodao")
                || ua.contains("MSNBot")
                || ua.contains("spider")
                || ua.contains("Twiceler")
                || ua.contains("Sosoimagespider")
                || ua.contains("naver.com/robots")
                || ua.contains("Nutch")
                || ua.contains("spider"));
    }

}
