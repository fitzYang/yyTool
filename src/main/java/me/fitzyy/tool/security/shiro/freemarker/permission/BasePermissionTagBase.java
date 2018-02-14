/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.security.shiro.freemarker.permission;


import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;

import java.io.IOException;
import java.util.Map;

import me.fitzyy.tool.security.shiro.freemarker.auth.BaseSecureTag;

/**
 * <p> . </p>
 *
 * @author FitzYang
 * @version 1.0 2012-10-27 10:37 AM
 * @since JDK 1.5
 */
public abstract class BasePermissionTagBase extends BaseSecureTag {

    @Override
    protected void verifyParameters(Map params) throws TemplateModelException {
        String permission = getName(params);

        if (permission == null || permission.length() == 0) {
            throw new TemplateModelException("The 'name' tag attribute must be set.");
        }
    }

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        String p = getName(params);

        boolean show = showTagBody(p);
        if (show) {
            renderBody(env, body);
        }
    }

    protected boolean isPermitted(String p) {
        return getSubject() != null && getSubject().isPermitted(p);
    }

    protected abstract boolean showTagBody(String p);

    private String getName(Map params) {
        return getParam(params, "name");
    }
}