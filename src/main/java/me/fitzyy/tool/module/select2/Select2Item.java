/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.module.select2;

import java.io.Serializable;

import lombok.Data;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 16:07  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class Select2Item implements Serializable {

    private static final long serialVersionUID = -876305485960586514L;
    private String id;

    private String text;
}
