/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.param;

import lombok.Builder;
import lombok.Data;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Data
@Builder
public class MarRfcException {

    private String paraId;

    private String  paraName;
    private String  paraDesc;
    private int     sortno;
    private boolean optional;
}
