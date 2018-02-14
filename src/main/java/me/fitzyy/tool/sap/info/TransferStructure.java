/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.info;

import com.google.common.collect.Maps;

import java.util.Map;

import me.fitzyy.tool.sap.model.MarTransferStructure;


/**
 * 数据传输结构信息
 *
 * @author sogyf
 * @version 1.0.0
 */
public class TransferStructure implements MarTransferStructure, java.io.Serializable {
    /**
     * serialVersionUID:
     */

    private static final long serialVersionUID = 6811560454268341532L;
    /**
     * structureName: 结构名称
     */
    private String structureName;
    /**
     * structureMap: 结构映射关系,将相应结构里面的参数作HashMap映射
     */
    private Map<String, Object> structureMap = Maps.newHashMap();


    public TransferStructure() {
        super();
    }


    public TransferStructure(String structureName) {
        super();
        this.structureName = structureName;
    }

    @Override
    public void setValue(String key, Object value) {
        structureMap.put(key, value);
    }


    @Override
    public String getStructureName() {
        return structureName;
    }

    @Override
    public void setStructureName(String structureName) {
        this.structureName = structureName;
    }


    @Override
    public Map<String, Object> getStructureMap() {
        return structureMap;
    }


    @Override
    public void setStructureMap(Map<String, Object> structureMap) {
        this.structureMap = structureMap;
    }


}
