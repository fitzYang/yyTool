/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.param;

import java.util.List;

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
public class MarRfcParam {
    private String rfcId;

    private String  paraName;
    private String  marName;
    private String  paraType;
    private String  marType;
    private String  paraDesc;
    private String  marDesc;
    private String  sortno;
    private String  paraId;
    private boolean paraOptional;
    private boolean leaf;
    private int     paraLength;
    private int     marLength;
    private int     paraDecimals;
    private int     marDecimals;
    private int     parentId;

    private List<MarRfcParam> childrens;
}
