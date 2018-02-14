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
 * <p> 定义一个被填充的模板自定义指令，一般在模板中使用，表示这个区域将要被子画面给填充掉. </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2012-04-20 上午7:36
 * @since JDK 1.5
 */
public class BlockDirective implements TemplateDirectiveModel {
    /**
     * 自定义指令名称
     */
    public final static String DIRECTIVE_NAME = "block";

    @SuppressWarnings("rawtypes")
    @Override
    public void execute(Environment env,
                        Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body) throws TemplateException, IOException {
        String name = DirectiveUtil.getRequiredParam(params, "name");
        TemplateDirectiveBodyOverrideWraper overrideBody = DirectiveUtil.getOverrideBody(env, name);
        if (overrideBody == null) {
            if (body != null) {
                body.render(env.getOut());
            }
        } else {
            DirectiveUtil.setTopBodyForParentBody(
                    new TemplateDirectiveBodyOverrideWraper(body, env),
                    overrideBody);
            overrideBody.render(env.getOut());
        }
    }

}
