/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.security.shiro.freemarker.role;


import org.apache.shiro.subject.Subject;

import me.fitzyy.tool.StringPool;

/**
 * <p> 多个角色的验证. </p>
 *
 * @author poplar.yfyang
 * @version 1.0 2012-10-27 10:35 AM
 * @since JDK 1.5
 */
public class HasAnyRolesTagBase extends BaseRoleTag {
    // Delimeter that separates role names in tag attribute

    @Override
    protected boolean showTagBody(String roleName) {
        boolean hasAnyRole = false;
        Subject subject = getSubject();

        if (subject != null) {
            // Iterate through roles and check to see if the user has one of the roles
            for (String role : roleName.split(StringPool.COMMA)) {
                if (subject.hasRole(role.trim())) {
                    hasAnyRole = true;
                    break;
                }
            }
        }

        return hasAnyRole;
    }
}
