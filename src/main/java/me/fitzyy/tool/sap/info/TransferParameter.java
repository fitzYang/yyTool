/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.info;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

import me.fitzyy.tool.sap.model.MarTransferParameter;
import me.fitzyy.tool.sap.model.MarTransferStructure;


/**
 * 数据传输入参数
 *
 * @author longhaul-hp  2011-12-7  下午06:05:24
 * @version 1.0.0
 */
public class TransferParameter implements MarTransferParameter, java.io.Serializable {

    /**
     * serialVersionUID:
     */

    private static final long                       serialVersionUID = -6037933914373958409L;
    /**
     * structureList: 传输结构
     */
    private              List<MarTransferStructure> structureList    = Lists.newArrayList();
    /**
     * parameters: 传输参数
     */
    private              Map<String, Object>        parameters       = Maps.newHashMap();


    @Override
    public void appendStructure(MarTransferStructure structure) {
        structureList.add(structure);

    }

    @Override
    public void setParameter(String key, Object value) {
        parameters.put(key, value);
    }

    @Override
    public List<MarTransferStructure> getStructureList() {
        return structureList;
    }

    @Override
    public void setStructureList(List<MarTransferStructure> structureList) {
        this.structureList = structureList;
    }

    @Override
    public Map<String, Object> getParameters() {
        return parameters;
    }

    @Override
    public void setParameters(Map<String, Object> parameters) {
        this.parameters = parameters;
    }

    @Override
    public MarTransferStructure getTransStructure(String structureName) {
        return new TransferStructure(structureName);
    }

}
