/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.security.shiro.freemarker.role;


import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import java.io.IOException;
import java.util.Map;

import me.fitzyy.tool.security.shiro.freemarker.auth.BaseSecureTag;

/**
 * <p> 角色标签，用于判定当前登录人的角色信息. </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2012-10-27 10:34 AM
 * @since JDK 1.5
 */
public abstract class BaseRoleTag extends BaseSecureTag {

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        boolean show = showTagBody(getName(params));
        if (show) {
            renderBody(env, body);
        }
    }

    protected abstract boolean showTagBody(String roleName);

    /**
     * 获取角色名称，从但前的参数信息中.
     *
     * @param params 参数信息
     * @return 角色名称
     */
    private String getName(Map params) {
        return getParam(params, "name");
    }
}