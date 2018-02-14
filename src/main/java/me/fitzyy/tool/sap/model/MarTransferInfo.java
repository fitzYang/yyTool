/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.model;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

/**
 * 数据传输接口,负责客户端到AIG数据传输
 *
 * @author longhaul-hp
 */
public interface MarTransferInfo {
    /**
     * 增加表到传输对象中
     */
    void appendTable(MarTransferTable table);

    /**
     * 获取一个表结构
     */
    MarTransferTable getTable(String talbename);

    /**
     * 获取表一个传输参数
     */
    MarTransferParameter getTransParameter();

    /**
     * 得到输入参数
     *
     * @return 输入参数
     */
    MarTransferParameter getImportPara();

    /**
     * 将输入参数设置到传输对象中
     *
     * @param importPara 　输入结构
     */
    void setImportPara(MarTransferParameter importPara);

    /**
     * 得到调用函数名称
     *
     * @return 函数名称
     */
    String getFunctionName();

    /**
     * 设置函数名称
     *
     * @param functionName 函数名称
     */
    void setFunctionName(String functionName);

    /**
     * 传输Table 参数
     *
     * @return 函数输出Table
     */
    List<MarTransferTable> getTableList();

    /**
     * 设置函数Table信息
     *
     * @param tableList Table 传输信息
     */
    void setTableList(List<MarTransferTable> tableList);

    /**
     * 得到输出参数列表
     *
     * @return 　输出参数列表
     */
    MarTransferParameter getExportPara();

    /**
     * 设置输出列表
     *
     * @param exportPara
     */
    void setExportPara(MarTransferParameter exportPara);


    /**
     * @param tableName 需要获取返回数据类型的表名
     * @return 返回表列表
     */
    List<Map<String, Object>> getAigTable(String tableName);

    /**
     * @param tableName 需要获取返回数据类型的表名
     * @return 返回JSON列表
     */
    List<JSONObject> getJSONAigTable(String tableName);

    /**
     * @param tableName 需要获取返回数据类型的表名
     * @return 返回JSON串
     */
    JSONObject getJsonAigTalbeString(String tableName);

    /**
     * @param structcturename 要获取结构的名称
     * @return 返回结构对应的Map
     */
    Map<?, ?> getAigStructure(String structcturename);

    /**
     * @param paraName 参数名称
     * @return 返回参数对应值
     */
    Object getParameters(String paraName);

    /**
     * @return 得到执行AIG信息
     */
    String getExecuteResultInfo();

    /**
     * @param executeInfo 　设置执行信息
     */
    void setExecuteResultInfo(String executeInfo);

    /**
     * @param tableName 需要获取返回数据类型的表名
     * @return 返回表名的XML串
     */
    String getXmlAigTable(String tableName) throws Exception;


}
