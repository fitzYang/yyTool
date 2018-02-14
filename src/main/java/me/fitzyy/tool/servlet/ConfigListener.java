/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.servlet;

import com.google.common.collect.Maps;

import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import static me.fitzyy.tool.StringPool.EMPTY;
import static me.fitzyy.tool.StringPool.SLASH;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings("unused")
public class ConfigListener implements ServletContextListener {

    private static Map<String, String> conf = Maps.newHashMap();

    public static Map<String, String> getConf() {
        return conf;
    }

    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        conf.clear();
    }

    @Override
    public void contextInitialized(ServletContextEvent evt) {
        ServletContext sc = evt.getServletContext();
        // 项目路径
        conf.put("realPath", sc.getRealPath(SLASH).replaceFirst(SLASH, EMPTY));
        conf.put("ctx", sc.getContextPath());
    }

}
