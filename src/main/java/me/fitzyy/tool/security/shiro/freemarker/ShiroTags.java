/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.security.shiro.freemarker;


import freemarker.template.ObjectWrapper;
import freemarker.template.SimpleHash;

import me.fitzyy.tool.security.shiro.freemarker.auth.AuthenticatedTagBase;
import me.fitzyy.tool.security.shiro.freemarker.auth.GuestTagBase;
import me.fitzyy.tool.security.shiro.freemarker.auth.NotAuthenticatedTagBase;
import me.fitzyy.tool.security.shiro.freemarker.auth.PrincipalTagBase;
import me.fitzyy.tool.security.shiro.freemarker.auth.UserTagBase;
import me.fitzyy.tool.security.shiro.freemarker.permission.HasAnyPermissionsTagBaseBase;
import me.fitzyy.tool.security.shiro.freemarker.permission.HasBasePermissionTagBase;
import me.fitzyy.tool.security.shiro.freemarker.permission.LacksBasePermissionTagBase;
import me.fitzyy.tool.security.shiro.freemarker.role.HasAnyRolesTagBase;
import me.fitzyy.tool.security.shiro.freemarker.role.HasBaseRoleTag;
import me.fitzyy.tool.security.shiro.freemarker.role.LacksBaseRoleTag;

/**
 * <p> Apache Shirio authentication Freemarker instructions. </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2012-10-27 10:42 AM
 * @since JDK 1.5
 */
public class ShiroTags extends SimpleHash {

    private static final long serialVersionUID = -7857361083450860303L;

    /**
     * Constructs an empty hash that uses the default wrapper set in
     */
    public ShiroTags() {
        super((ObjectWrapper) null);
        put("authenticated", new AuthenticatedTagBase());
        put("guest", new GuestTagBase());
        put("hasAnyRoles", new HasAnyRolesTagBase());
        put("hasPermission", new HasBasePermissionTagBase());
        put("hasAnyPermissions", new HasAnyPermissionsTagBaseBase());
        put("hasRole", new HasBaseRoleTag());
        put("lacksPermission", new LacksBasePermissionTagBase());
        put("lacksRole", new LacksBaseRoleTag());
        put("notAuthenticated", new NotAuthenticatedTagBase());
        put("principal", new PrincipalTagBase());
        put("user", new UserTagBase());
    }
}
