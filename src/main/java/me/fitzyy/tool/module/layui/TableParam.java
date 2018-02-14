/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.module.layui;

import java.io.Serializable;

import lombok.Data;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 16:09  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class TableParam<Q> implements Serializable {
    private static final long serialVersionUID = -2883180102819042257L;


    private int    page;
    private int    limit;
    private String field;
    private String order;

    private Q query;

}
