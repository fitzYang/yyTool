/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.info;

import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

import me.fitzyy.tool.sap.model.MarTransferInfo;
import me.fitzyy.tool.sap.model.MarTransferParameter;
import me.fitzyy.tool.sap.model.MarTransferStructure;
import me.fitzyy.tool.sap.model.MarTransferTable;


/**
 * 中间传输对象,用于客户端传递数据到中间件和中间件返回SAP对象<br>对象包括调用函数名,输入参数,Table列表和输入参数
 *
 * @author longhaul-hp
 * @version 1.0.0
 */
public class TransferInfo implements MarTransferInfo, java.io.Serializable {


    /**
     * serialVersionUID:
     */

    private static final long serialVersionUID = 1L;

    /**
     * functionName:调用方法名称
     */
    private String functionName;

    /**
     * importPara:输入参数列表
     */
    private MarTransferParameter importPara = new TransferParameter();

    /**
     * tableList: 输入table 列表
     */
    private List<MarTransferTable> tableList = Lists.newArrayList();

    /**
     * exportPara: 输入出参数列表
     */
    private MarTransferParameter exportPara = new TransferParameter();

    /**
     * exportPara: 执行返回信息
     */
    private String executeResultInfo;


    public TransferInfo() {
        super();
    }


    @Override
    public void appendTable(MarTransferTable table) {
        tableList.add(table);
    }


    @Override
    public MarTransferParameter getImportPara() {
        return importPara;
    }


    @Override
    public void setImportPara(MarTransferParameter importPara) {
        this.importPara = importPara;
    }


    @Override

    public String getFunctionName() {
        return functionName;
    }

    @Override
    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public String getExecuteResultInfo() {
        return executeResultInfo;
    }

    @Override
    public void setExecuteResultInfo(String executeResultInfo) {
        String crlf = System.getProperty("line.separator");
        this.executeResultInfo = this.executeResultInfo + crlf + executeResultInfo;
    }

    @Override
    public List<MarTransferTable> getTableList() {
        return tableList;
    }


    @Override
    public void setTableList(List<MarTransferTable> tableList) {
        this.tableList = tableList;
    }


    @Override
    public MarTransferParameter getExportPara() {
        return exportPara;
    }


    @Override
    public void setExportPara(MarTransferParameter exportPara) {
        this.exportPara = exportPara;
    }


    @Override
    public MarTransferTable getTable(String talbename) {
        return new TransferTable(talbename);

    }


    @Override
    public MarTransferParameter getTransParameter() {
        return new TransferParameter();
    }


    @Override
    public List<Map<String, Object>> getAigTable(String tableName) {
        List<MarTransferTable> tableList = this.getTableList();
        List<Map<String, Object>> talbeconent = Lists.newArrayList();
        for (MarTransferTable aTableList : tableList) {
            String gettableName = aTableList.getName();
            if (gettableName.equals(tableName)) {
                talbeconent = aTableList.getMetaData();
                break;
            }
        }
        return talbeconent;
    }


    @Override
    public Map<?, ?> getAigStructure(String structcturename) {
        MarTransferParameter outpara = this.getExportPara();
        Map<?, ?> structuresMap = null;
        List<MarTransferStructure> list = outpara.getStructureList();
        for (MarTransferStructure struts : list) {
            if (structcturename.equals(struts.getStructureName())) {
                structuresMap = struts.getStructureMap();
                break;
            }
        }
        return structuresMap;
    }


    @Override
    public Object getParameters(String paraName) {
        return this.getExportPara().getParameters().get(paraName.trim());
    }


    @Override
    public List<JSONObject> getJSONAigTable(String tableName) {
        return null;
    }


    @Override
    public JSONObject getJsonAigTalbeString(String tableName) {
        // TODO Auto-generated method stub
        return null;
    }


    @Override
    public String getXmlAigTable(String tableName) {
        // TODO Auto-generated method stub
        return null;
    }


}
