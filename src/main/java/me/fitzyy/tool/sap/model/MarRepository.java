/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.model;


import me.fitzyy.tool.sap.info.TransferInfo;

public abstract class MarRepository {

    public static MarTransferInfo getTransferInfo() {
        return new TransferInfo();
    }

}
