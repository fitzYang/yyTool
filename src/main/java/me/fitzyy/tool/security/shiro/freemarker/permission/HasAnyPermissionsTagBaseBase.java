/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.security.shiro.freemarker.permission;


import org.apache.shiro.subject.Subject;

import me.fitzyy.tool.StringPool;

/**
 * <p> A plurality of permission, as long as the only one. </p> <p/> Note: From the <a
 * href="https://github.com/springside/springside4">SpringSide4</a>
 *
 * @author sogYF
 * @version 1.0
 * @since JDK 1.6
 */
public class HasAnyPermissionsTagBaseBase extends BasePermissionTagBase {
    @Override
    protected boolean showTagBody(String permissionNames) {
        boolean hasAnyPermission = false;

        Subject subject = getSubject();

        if (subject != null) {
            // Iterate through permissions and check to see if the user has one of the permissions
            for (String permission : permissionNames.split(StringPool.COMMA)) {

                if (subject.isPermitted(permission.trim())) {
                    hasAnyPermission = true;
                    break;
                }

            }
        }

        return hasAnyPermission;
    }
}
