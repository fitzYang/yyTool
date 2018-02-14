/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.model;

import java.util.List;
import java.util.Map;

/**
 * 传输表信息
 *
 * @author longhaul-hp
 */
public interface MarTransferTable {
    /**
     * 增加行到传输表对象
     */
    void appendRow();

    /**
     * @param key   HahMap 键
     * @param value 　 HahMap 值
     */
    void setValue(String key, Object value);

    /**
     * 得到talb 名称
     *
     * @return Table 名称
     */
    String getName();

    /**
     * 设置传输表名称
     *
     * @param name 传输表名称
     */
    void setName(String name);

    Map<String, Object> getTableValue();

    void setTableValue(Map<String, Object> tableValue);

    /**
     * 得到表传输内容
     *
     * @return 传表内容
     */
    List<Map<String, Object>> getMetaData();

    /**
     * 设置传表内容
     *
     * @param metaData 表内容
     */
    void setMetaData(List<Map<String, Object>> metaData);

}
