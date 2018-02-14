/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.info;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;

import me.fitzyy.tool.sap.model.MarTransferTable;


/**
 * 传输对象-表．
 *
 * @author longhaul-hp  2011-12-7  下午06:18:26
 * @version 1.0.0
 */

/**
 * @author hepeng
 */
public class TransferTable implements MarTransferTable, java.io.Serializable {
    /**
     * serialVersionUID:
     */

    private static final long serialVersionUID = 2807897344923010381L;
    /**
     * name: 传输入表名
     */
    private String name;
    /**
     * tableValue: 作为预存表的中间值,以HashMap 形式暂存表中间值
     */
    private Map<String, Object>       tableValue = Maps.newHashMap();
    /**
     * metaData:  表的数据集
     */
    private List<Map<String, Object>> metaData   = Lists.newArrayList();


    public TransferTable() {
        super();
    }

    public TransferTable(String name) {
        super();
        this.name = name;
    }


    @Override
    public void setValue(String key, Object value) {
        tableValue.put(key, value);
    }


    @Override
    public void appendRow() {
        metaData.add(tableValue);
        tableValue = Maps.newHashMap();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Map<String, Object> getTableValue() {
        return tableValue;
    }

    @Override
    public void setTableValue(Map<String, Object> tableValue) {
        this.tableValue = tableValue;
    }

    @Override
    public List<Map<String, Object>> getMetaData() {
        return metaData;
    }

    @Override
    public void setMetaData(List<Map<String, Object>> metaData) {
        this.metaData = metaData;
    }

}
