/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.module.select2;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 16:08  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class Select2Data implements Serializable {
    private static final long serialVersionUID = -8905560634733266143L;


    private List<Select2Item> results;

    private int total;
}
