/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.module.freemarker.layout;


import freemarker.core.Environment;
import freemarker.template.Configuration;
import freemarker.template.SimpleScalar;
import freemarker.template.Template;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.util.Map;

import me.fitzyy.tool.StringPool;
import me.fitzyy.tool.module.freemarker.util.DirectiveUtil;

/**
 * <p> 定义模板继承的自定义指令. </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2012-04-20 上午7:36
 * @since JDK 1.5
 */
public class ExtendsDirective implements TemplateDirectiveModel {
    /**
     * 自定义指令名称
     */
    public final static String DIRECTIVE_NAME = "extends";

    @SuppressWarnings("rawtypes")
    @Override
    public void execute(Environment env, Map params,
                        TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
        String name = DirectiveUtil.getRequiredParam(params, "name");
        params.remove("name");

        if (!name.endsWith(".ftl")) {
            name = name + ".ftl";
        }
        String encoding = DirectiveUtil.getParam(params, "encoding", StringPool.UTF_8);
        final String templatePath = getTemplatePath(env);
        String includeTemplateName = env.toFullTemplateName(templatePath, name);
        Configuration configuration = env.getConfiguration();
        final Template template = configuration.getTemplate(includeTemplateName, env.getLocale(), encoding, true);
        for (Object key : params.keySet()) {
            TemplateModel paramModule = new SimpleScalar(params.get(key).toString());
            env.setVariable(key.toString(), paramModule);
        }
        env.include(template);
    }

    /**
     * 取得模板路径的地址
     *
     * @param env Freemarker的运行环境
     * @return 模板路径地址
     */
    private String getTemplatePath(Environment env) {
        String templateName = env.getMainTemplate().getName();
        return templateName.lastIndexOf('/') == -1 ? "" : templateName.substring(0, templateName.lastIndexOf('/') + 1);
    }

}
