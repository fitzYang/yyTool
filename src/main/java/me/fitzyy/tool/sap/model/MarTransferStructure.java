/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.model;

import java.util.Map;

/**
 * 传输结构类型
 *
 * @author longhaul-hp
 */
public interface MarTransferStructure {
    /**
     * 设置结构值
     *
     * @param key   键
     * @param value 　值
     */
    void setValue(String key, Object value);

    /**
     * 得到结构名称
     *
     * @return 结构名称
     */
    String getStructureName();

    /**
     * 设置结构名称
     *
     * @param structureName 结构名称
     */
    void setStructureName(String structureName);

    /**
     * 得到结构对象
     *
     * @return 结构值
     */
    Map<String, Object> getStructureMap();

    /**
     * 设置结构对象
     *
     * @param structureMap 结构MAP
     */
    void setStructureMap(Map<String, Object> structureMap);


}
