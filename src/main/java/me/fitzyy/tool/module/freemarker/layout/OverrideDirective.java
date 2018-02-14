/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.module.freemarker.layout;


import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.util.Map;

import me.fitzyy.tool.module.freemarker.util.DirectiveUtil;
import me.fitzyy.tool.module.freemarker.util.TemplateDirectiveBodyOverrideWraper;

/**
 * <p> Freemarker的覆盖自定义标记，用来覆盖模板中的指定区域. </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2012-04-20 上午7:32
 * @since JDK 1.5
 */
public class OverrideDirective implements TemplateDirectiveModel {
    /**
     * 覆盖模板的自定义指令名称
     */
    public final static String DIRECTIVE_NAME = "override";

    @SuppressWarnings("rawtypes")
    @Override
    public void execute(Environment env, Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {
        String name = DirectiveUtil.getRequiredParam(params, "name");
        String overrideVariableName = DirectiveUtil.getOverrideVariableName(name);

        TemplateDirectiveBodyOverrideWraper override = DirectiveUtil.getOverrideBody(env, name);
        TemplateDirectiveBodyOverrideWraper current = new TemplateDirectiveBodyOverrideWraper(body, env);
        if (override == null) {
            env.setVariable(overrideVariableName, current);
        } else {
            DirectiveUtil.setTopBodyForParentBody(current, override);
        }
    }

}
