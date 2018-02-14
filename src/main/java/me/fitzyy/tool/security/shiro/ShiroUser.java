/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.security.shiro;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * <p>自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Data
@Builder
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = 7525844345413710498L;


    private long         id;          // 主键ID
    private String       account;      // 账号
    private String       name;         // 姓名
    private long         deptId;      // 部门id
    private List<Long>   roleList; // 角色集
    private String       deptName;        // 部门名称
    private List<String> roleNames; // 角色名称集

}
