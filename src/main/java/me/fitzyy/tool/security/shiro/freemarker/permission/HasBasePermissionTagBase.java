/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.security.shiro.freemarker.permission;

/**
 * <p> . </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2012-10-27 10:38 AM
 * @since JDK 1.5
 */
public class HasBasePermissionTagBase extends BasePermissionTagBase {
    @Override
    protected boolean showTagBody(String p) {
        return isPermitted(p);
    }
}
