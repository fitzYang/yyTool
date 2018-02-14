/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.font;

import java.io.Serializable;

import lombok.Data;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 16:16  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class FontParam implements Serializable {


    private static final long serialVersionUID = -4453240143661186579L;
    /**
     * 字体名称
     */
    private String family;

    /**
     * 字体大小
     */
    private float size;

    /**
     * 字体颜色
     */
    private String color;

    /**
     * 字体图片宽度
     */
    private int width;
    /**
     * 字体图片高度
     */
    private int height;
}
