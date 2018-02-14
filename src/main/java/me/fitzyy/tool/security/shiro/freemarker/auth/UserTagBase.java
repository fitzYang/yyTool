/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.security.shiro.freemarker.auth;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;

import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.Map;

/**
 * <p> . </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2012-10-27 10:28 AM
 * @since JDK 1.5
 */
public class UserTagBase extends BaseSecureTag {

    @Override
    public void render(Environment env, Map params, TemplateDirectiveBody body) throws IOException, TemplateException {
        final Subject subject = getSubject();
        if (subject != null && subject.getPrincipal() != null) {
            renderBody(env, body);
        } else {
            _logger.debug("Subject does not exist or have a known identity (aka 'principal'). Tag body will not be evaluated.");
        }
    }
}
