/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.model;

import java.util.List;
import java.util.Map;

/**
 * 传输结构参数
 *
 * @author longhaul-hp
 */
public interface MarTransferParameter {
    /**
     * 设置参数传输结构
     *
     * @param structure 传输对象
     */
    void appendStructure(MarTransferStructure structure);

    /**
     * @param structureName 传入要得到结构的名称
     * @return 返回来个传输结构类型
     */
    MarTransferStructure getTransStructure(String structureName);

    /**
     * 设置参数
     *
     * @param key   　对应键
     * @param value 　对应值
     */
    void setParameter(String key, Object value);

    /**
     * 得到参数结构列表
     *
     * @return 参数结构列表
     */
    List<MarTransferStructure> getStructureList();

    /**
     * 设置参数结构列表信息
     *
     * @param structureList 结构信息
     */
    void setStructureList(List<MarTransferStructure> structureList);

    /**
     * 得到参数值
     *
     * @return 参数值
     */
    Map<String, Object> getParameters();

    /**
     * 设置参数值
     *
     * @param parameters 参数HashMap
     */
    void setParameters(Map<String, Object> parameters);

}
