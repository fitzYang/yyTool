/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.module.freemarker;

import freemarker.template.Configuration;
import freemarker.template.Template;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import me.fitzyy.tool.lang.ExceptionUtil;


/**
 * <p> Freemarker 工具类</p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings("unused")
public class FreeMarkers {

    public static String renderString(String templateString, Map<String, ?> model) {
        try {
            StringWriter result = new StringWriter();
            Template t = new Template("name", new StringReader(templateString), new Configuration());
            t.process(model, result);
            return result.toString();
        } catch (Exception e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    public static String renderTemplate(Template template, Object model) {
        try {
            StringWriter result = new StringWriter();
            template.process(model, result);
            return result.toString();
        } catch (Exception e) {
            throw ExceptionUtil.unchecked(e);
        }
    }

    public static Configuration buildConfiguration(String directory) throws IOException {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_26);
        Resource path = new DefaultResourceLoader().getResource(directory);
        cfg.setDirectoryForTemplateLoading(path.getFile());
        return cfg;
    }
}
