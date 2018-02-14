/*
 * Copyright (c) 2018. Fitzyy
 * All Rights Reserved
 */

package me.fitzyy.tool.sap;

import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoParameterList;

import org.junit.Test;

import java.util.List;

import me.fitzyy.tool.sap.param.MarRfcParam;


/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class SapConnectionTest {

    @Test
    public void getFunction() {
    }

    @Test
    public void execute() {
        System.out.println("System.getProperty(\"java.library.path\") = " +
                System.getProperty("java.library.path"));

        SapProp sapProp = new SapProp("QQ_CRM", "218.22.45.92", "130", "00", "jingtong01", "654321", "ZH", "500");

        SapConnection sapConnection = new SapConnection(sapProp);


        final JCoFunction orgStruFunc = sapConnection.getFunction("ZCRM_INT_ORGSTRU");
        final JCoParameterList importParameterList = orgStruFunc.getImportParameterList();
        final List<MarRfcParam> orgParam = SapFunctionUtil.getParaList(importParameterList, "ZCRM_INT_ORGSTRU");

        System.out.println("orgParam = " + orgParam);
//
//        final JCoStructure ivRootObjid = importParameterList.getStructure("IV_ROOT_OBJID");
//        ivRootObjid.setValue("OTYPE", "O");
//        ivRootObjid.setValue("OBJID", "50000000");
//        importParameterList.setValue("IV_ACT_BEGDA", "20150101");
//        importParameterList.setValue("IV_ACT_ENDDA", "20171130");
//        sapConnection.execute(orgStruFunc);
//        final JCoParameterList exportParameterList = orgStruFunc.getExportParameterList();
//        final String evStatus = exportParameterList.getString("EV_STATUS");
//        System.out.println("evStatus = " + evStatus);
//
//        final JCoTable resultStruc = exportParameterList.getTable("ET_STRUC");
//        final int numRows = resultStruc.getNumRows();
//        for (int i = 0; i < numRows; i++) {
//            resultStruc.setRow(i);
//            final String seqnr = resultStruc.getString("SEQNR");
//            System.out.println("seqnr = " + seqnr);
//            final String seText = resultStruc.getString("STEXT");
//            System.out.println("seText = " + seText);
//            final String aShort = resultStruc.getString("SHORT");
//            System.out.println("SHORT = " + aShort);
//        }
    }
}