/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.servlet;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 16:32  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class JsessionIdFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            final String requestURI = httpRequest.getRequestURI();

            String requestURL = StringUtils.lowerCase(requestURI);

            int index = requestURL.indexOf(";jsessionid");
            if (index != -1) {
                HttpServletResponse httpServletResponse = (HttpServletResponse) response;
                final String cutJsessionIdURI = requestURL.substring(0, index);
                httpServletResponse.sendRedirect(cutJsessionIdURI);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
