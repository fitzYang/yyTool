/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap;

import com.google.common.collect.Lists;

import com.sap.conn.jco.AbapException;
import com.sap.conn.jco.JCoField;
import com.sap.conn.jco.JCoFieldIterator;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoListMetaData;
import com.sap.conn.jco.JCoMetaData;
import com.sap.conn.jco.JCoParameterFieldIterator;
import com.sap.conn.jco.JCoParameterList;
import com.sap.conn.jco.JCoStructure;
import com.sap.conn.jco.JCoTable;

import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.hutool.core.util.NumberUtil;
import me.fitzyy.tool.sap.info.TransferInfo;
import me.fitzyy.tool.sap.info.TransferParameter;
import me.fitzyy.tool.sap.info.TransferStructure;
import me.fitzyy.tool.sap.info.TransferTable;
import me.fitzyy.tool.sap.model.MarTransferInfo;
import me.fitzyy.tool.sap.model.MarTransferParameter;
import me.fitzyy.tool.sap.model.MarTransferStructure;
import me.fitzyy.tool.sap.model.MarTransferTable;
import me.fitzyy.tool.sap.param.MarRfcException;
import me.fitzyy.tool.sap.param.MarRfcParam;


/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings("unused")
public class SapFunctionUtil {

    private static final String SAP_TYPE_BCD     = "BCD";
    private static final String SAP_TYPE_INT     = "INT";
    private static final String SAP_TYPE_INT_1   = "INT1";
    private static final String SAP_TYPE_INT_2   = "INT2";
    private static final String SAP_TYPE_DECF_16 = "DECF16";
    private static final String SAP_TYPE_DECF_34 = "DECF34";
    private static final String SAP_TYPE_FLOAT   = "FLOAT";
    private static final String SORT_NO_FORMAT   = "000";

    private SapFunctionUtil() {
    }

    /**
     * 得到RFC输出有table列表
     *
     * @param paralist 函数参数列表
     * @param rfcId    rfc id
     * @return table列表
     */
    public static List<MarRfcParam> getTableList(JCoParameterList paralist, String rfcId) {
        List<MarRfcParam> rfcImportParameterList = Lists.newArrayList();
        JCoParameterFieldIterator itor = paralist.getParameterFieldIterator();
        Map<String, String> paraList;
        int i = 0;
        while (itor.hasNextField()) {
            JCoField tableFiled = itor.nextField();
            final String tableName = tableFiled.getName();
            JCoTable returnuTable = paralist.getTable(tableName);
            // 得到表的字段
            JCoFieldIterator it = returnuTable.getFieldIterator();

            final String classNameOfValue = tableFiled.getClassNameOfValue();
            final String description = tableFiled.getDescription();
            final int length = tableFiled.getLength();
            final String sortno = NumberUtil.decimalFormat(SORT_NO_FORMAT, i + 1);
            String paraId = rfcId + sortno;
            final MarRfcParam rfcParam = MarRfcParam.builder().rfcId(rfcId)
                    .paraName(tableName).marName(tableName)
                    .paraType(classNameOfValue).marType(classNameOfValue)
                    .paraDesc(description).marDesc(description)
                    .paraLength(length).marLength(length)
                    .leaf(false)
                    .sortno(sortno).paraId(paraId).parentId(0)
                    .build();

            rfcImportParameterList.add(rfcParam);


            i++;

            List<MarRfcParam> rfcParams = Lists.newArrayList();

            int j = 0;
            while (it.hasNextField()) {

                JCoField field = it.nextField();

                final String fieldClassNameOfValue = field.getClassNameOfValue();
                final String fieldDescription = field.getDescription();
                final String fieldName = field.getName();


                final MarRfcParam.MarRfcParamBuilder rfcParamBuilder = MarRfcParam.builder()
                        .rfcId(rfcId)
                        .paraName(fieldName).marName(fieldName)
                        .paraDesc(fieldDescription).marDesc(fieldDescription)
                        .paraType(fieldClassNameOfValue).marType(fieldClassNameOfValue);


                int paraLength = field.getLength();
                String ftype = field.getTypeAsString();
                if (ftype.equals(SAP_TYPE_BCD)) {
                    paraLength = paraLength * 2 - 1;
                }
                if (ftype.equals(SAP_TYPE_INT) || ftype.equals(SAP_TYPE_INT_1) || ftype.equals(SAP_TYPE_INT_2)) {
                    paraLength = paraLength * 2;
                }
                if (ftype.equals(SAP_TYPE_DECF_16) || ftype.equals(SAP_TYPE_DECF_34)) {
                    paraLength = paraLength * 2 - 1;
                }
                if (ftype.equals(SAP_TYPE_FLOAT)) {
                    paraLength = paraLength * 2 - 1;
                }
                final int fieldDecimals = field.getDecimals();
                if (paraLength >= fieldDecimals) {
                    rfcParamBuilder.paraLength(paraLength).marLength(paraLength);
                } else {
                    rfcParamBuilder.paraLength(fieldDecimals).marLength(fieldDecimals);
                }
                final String fieldSortno = NumberUtil.decimalFormat(SORT_NO_FORMAT, j + 1);
                String nextparaId = paraId + fieldSortno;
                final MarRfcParam fileRfcParam = rfcParamBuilder
                        .paraLength(length).marLength(length)
                        .leaf(true)
                        .sortno(fieldSortno).paraId(nextparaId).parentId(0)
                        .paraDecimals(fieldDecimals).marDecimals(fieldDecimals)
                        .build();
                rfcParams.add(rfcParam);
                rfcImportParameterList.add(rfcParam);

                j++;
            }

            rfcParam.setChildrens(rfcParams);
        }

        return rfcImportParameterList;
    }

    public static List<MarRfcException> getParaExceptions(AbapException[] abapExceptionArray, String rfcId) {
        List<MarRfcException> rfcExceptionsrList = Lists.newArrayList();
        if (abapExceptionArray != null) {
            for (int i = 0; i < abapExceptionArray.length; i++) {
                AbapException exception = abapExceptionArray[i];

                String paraId = NumberUtil.decimalFormat(SORT_NO_FORMAT, i + 1);
                final MarRfcException rfcException = MarRfcException.builder()
                        .paraId(paraId).paraName(exception.getKey())
                        .paraDesc(exception.getMessage())
                        .sortno(i).optional(false).build();

                rfcExceptionsrList.add(rfcException);
            }
        }
        return rfcExceptionsrList;
    }

    /**
     * 得到RFC参数列表
     *
     * @param paralist 参数信息
     * @param rfcId    rfcid
     * @return 参数列表
     */
    public static List<MarRfcParam> getParaList(JCoParameterList paralist, String rfcId) {
        if (paralist == null) {
            return null;
        }
        List<MarRfcParam> rfcParameterList = Lists.newArrayList();
        for (int i = 0; i < paralist.getFieldCount(); i++) {

            final JCoMetaData metaData = paralist.getMetaData();
            final JCoListMetaData listMetaData = paralist.getListMetaData();
            final boolean structure = metaData.isStructure(i);

            final MarRfcParam marRfcParam = metaToParam(rfcId, i, metaData, listMetaData);
            int paraLength;
            String ftype;
            rfcParameterList.add(marRfcParam);
            if (structure) {
                // 如果是结构

                List<MarRfcParam> childrens = Lists.newArrayList();

                JCoMetaData jcometadata = metaData.getRecordMetaData(i);
                for (int j = 0; j < jcometadata.getFieldCount(); j++) {
                    final MarRfcParam fileParam = metaToParam(rfcId, j, jcometadata, listMetaData);

                    final String sortno = NumberUtil.decimalFormat(SORT_NO_FORMAT, j + 1);
                    fileParam.setSortno(sortno);
                    String nextparaId = marRfcParam.getParaId() + sortno;
                    fileParam.setLeaf(true);
                    fileParam.setParaId(nextparaId);

                    childrens.add(fileParam);

                    rfcParameterList.add(fileParam);
                }
                marRfcParam.setChildrens(childrens);
            }
        }
        return rfcParameterList;
    }

    /**
     * 设置输入RFC输入参数
     *
     * @param rfctransferinfo 传输对象
     * @param function        RFC函数
     */
    public static void setFunctionParas(MarTransferInfo rfctransferinfo, JCoFunction function) {
        com.sap.conn.jco.JCoParameterList paras = function.getImportParameterList();
        MarTransferParameter rfcimport = rfctransferinfo.getImportPara();
        // 设置输入结构
        for (int i = 0; i < rfcimport.getStructureList().size(); i++) {
            MarTransferStructure rfcStructure = rfcimport.getStructureList().get(i);
            String structureName = rfcStructure.getStructureName();
            JCoStructure structure = paras.getStructure(structureName);
            Map<?, ?> map = rfcStructure.getStructureMap();
            for (Map.Entry<?, ?> entry : map.entrySet()) {
                structure.setValue(entry.getKey().toString(), entry.getValue());
            }
        }
        // 设置输入参数
        Map<String, Object> inputs = rfcimport.getParameters();
        for (Map.Entry<String, Object> mapValue : inputs.entrySet()) {
            paras.setValue(mapValue.getKey(), mapValue.getValue());
        }
        // 设置输入表
        for (int t = 0; t < rfctransferinfo.getTableList().size(); t++) {
            String tablename = rfctransferinfo.getTableList().get(t).getName();
            com.sap.conn.jco.JCoTable table = function.getTableParameterList().getTable(tablename);

            table.deleteAllRows();
            MarTransferTable transRfctable = rfctransferinfo.getTableList().get(t);
            for (int i = 0; i < transRfctable.getMetaData().size(); i++) {
                table.appendRow();// 增加表内容
                Map<?, ?> map = transRfctable.getMetaData().get(i);
                for (Map.Entry<?, ?> mapValue : map.entrySet()) {
                    table.setValue(mapValue.getKey().toString(), mapValue.getValue());
                }
            }
            // System.out.println(table);
        }
    }

    /**
     * 执行RFC函数
     *
     * @param function       　　RFC函数
     * @param inTransferInfo 输入参数
     * @return 执行结果
     */
    public static TransferInfo functionExectue(JCoFunction function, MarTransferInfo inTransferInfo) {
        JCoParameterList export = function.getExportParameterList();
        TransferParameter rfcExport = new TransferParameter();
        TransferInfo outFunctionInfo = new TransferInfo();
        if (export != null) {
            // 输出列表
            for (int i = 0; i < export.getFieldCount(); i++) {
                String exportName = export.getMetaData().getName(i);
                if (!export.getMetaData().isStructure(i)) {
                    rfcExport.setParameter(exportName, export.getString(exportName));
                } else {
                    TransferStructure rfcstructure = new TransferStructure();
                    JCoMetaData jcometadata = export.getMetaData().getRecordMetaData(i);
                    rfcstructure.setStructureName(exportName);
                    JCoStructure returnStructure = (JCoStructure) export.getValue(exportName);
                    for (int j = 0; j < jcometadata.getFieldCount(); j++) {
                        rfcstructure.setValue(jcometadata.getName(j), returnStructure.getString(jcometadata.getName(j)));
                    }
                    rfcExport.appendStructure(rfcstructure);
                }
            }
        }
        outFunctionInfo.setExportPara(rfcExport);
        if (function.getTableParameterList() != null) {
            // 输出表结构
            if (function.getTableParameterList().getFieldCount() > 0) {
                for (int k = 0; k < function.getTableParameterList().getFieldCount(); k++) {
                    TransferTable outRfctable = new TransferTable();
                    String tablename = function.getTableParameterList().getMetaData().getName(k);
                    JCoTable returnuTable = function.getTableParameterList().getTable(tablename);
                    outRfctable.setName(tablename);
                    if (returnuTable.getNumRows() > 0) {
                        // 得到输入出表的大小
                        do {
                            for (JCoFieldIterator e = returnuTable.getFieldIterator(); e.hasNextField(); ) {
                                JCoField field = e.nextField();
                                String fieldValue = field.getString();
                                outRfctable.setValue(field.getName(), fieldValue);
                                isCheckSelect(inTransferInfo, outRfctable, field.getName());
                            }
                            outRfctable.appendRow(); // 增加表
                        } while (returnuTable.nextRow());
                    }
                    outFunctionInfo.appendTable(outRfctable);
                }
            }
        }
        return outFunctionInfo;
    }

    private static MarRfcParam metaToParam(String rfcId, int i, JCoMetaData metaData, JCoListMetaData listMetaData) {
        final String name = metaData.getName(i);
        final String classNameOfField = metaData.getClassNameOfField(i);
        final String description = metaData.getDescription(i);

        final boolean optional = listMetaData.isOptional(i);

        final MarRfcParam.MarRfcParamBuilder paramBuilder = MarRfcParam.builder()
                .rfcId(rfcId)
                .paraName(name).marName(name)
                .paraType(classNameOfField).marName(classNameOfField)
                .paraDesc(description).marDesc(description)
                .paraOptional(optional)
                .leaf(metaData.isStructure(i));

        int paraLength = metaData.getLength(i);
        String ftype = metaData.getTypeAsString(i);
        if (ftype.equals(SAP_TYPE_BCD)) {
            paraLength = paraLength * 2 - 1;
        }
        if (ftype.equals(SAP_TYPE_INT) || ftype.equals(SAP_TYPE_INT_1) || ftype.equals(SAP_TYPE_INT_2)) {
            paraLength = paraLength * 2;
        }
        if (ftype.equals(SAP_TYPE_DECF_16) || ftype.equals(SAP_TYPE_DECF_34)) {
            paraLength = paraLength * 2 - 1;
        }
        if (ftype.equals(SAP_TYPE_FLOAT)) {
            paraLength = paraLength * 2 - 1;
        }
        final int decimals = metaData.getDecimals(i);
        if (paraLength >= decimals) {
            paramBuilder.paraLength(paraLength).marLength(paraLength);
        } else {
            paramBuilder.paraLength(decimals).marLength(decimals);
        }
        final String sortNo = NumberUtil.decimalFormat(SORT_NO_FORMAT, i + 1);
        String paraId = rfcId + sortNo;
        return paramBuilder
                .paraDecimals(decimals).marDecimals(decimals).parentId(0)
                .sortno(sortNo).paraId(paraId).build();
    }

    /**
     * 检查import字段是否在检查输出表字段中 如果在表字段中把输入的值增加到表中，供本地查询的时候用
     *
     * @param inTransferInfo 输入信息
     * @param outRfctable    输出表
     * @param tableFiledName 　表字段名称
     */
    private static void isCheckSelect(MarTransferInfo inTransferInfo, TransferTable outRfctable, String tableFiledName) {
        // 得到输入参数作分页处理将输输入参数增加到表中
        if (inTransferInfo != null) {
            MarTransferParameter rfcimport = inTransferInfo.getImportPara();
            Map<String, Object> inputs = rfcimport.getParameters();
            Set<String> set = inputs.keySet();
            for (String keyName : set) {
                Object value = inputs.get(keyName);
                if (keyName.equals("I_" + tableFiledName)) {
                    outRfctable.setValue(tableFiledName, value);
                }
            }
        }

    }
}
