/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.module.ztree.domain;


import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * <p>ztree类
 * </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings("unused")
@Data
@Builder
public class ZtreeVO<D extends Serializable> implements Serializable {
    private static final long serialVersionUID = 4858257964310637000L;
    /**
     * 结点编号
     */
    private String id;

    /**
     * 父级id
     */
    private String pid;

    /**
     * 结点名称
     */
    private String name;

    /**
     * 是否勾选
     */
    private Boolean checked;
    /**
     * 设置节点是否隐藏 checkbox / radio
     */
    private Boolean nocheck;

    /**
     * 是否是叶子节点
     */
    @Builder.Default
    private Boolean open = true;

    /**
     * 是否禁止勾选
     */
    private Boolean chkDisabled;

    /**
     * 是否半选
     */
    private Boolean halfCheck;

    /**
     * 是否隐藏
     */
    private Boolean hidden;

    /**
     * 是否是父结点
     */
    private Boolean parent;

    /**
     * 结点的子结点，如果是父结点，则存在子结点
     */
    private List<ZtreeVO<D>> children;

    /**
     * 结点点击跳转的地址
     */
    private String url;

    /**
     * 结点点击跳转目标
     */
    private String target;

    /**
     * 结点上的应用数据
     */
    private D data;
}
