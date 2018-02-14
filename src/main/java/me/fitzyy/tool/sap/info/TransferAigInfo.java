/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap.info;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * @author longhaul-hp
 * 传输入AIG信息
 */
public class TransferAigInfo {
    private String                             rfcname;
    private String                             rfc_id;
    private ArrayList<HashMap<String, String>> rfcImportParameterList;
    private ArrayList<HashMap<String, String>> rfcExportParameterList;
    private ArrayList<HashMap<String, String>> rfcChangingParameterList;
    private ArrayList<HashMap<String, String>> rfcTalbeParaMeterList;
    private ArrayList<HashMap<String, String>> rfCExceptionList;

    public String getRfcname() {
        return rfcname;
    }

    public void setRfcname(String rfcname) {
        this.rfcname = rfcname;
    }

    public String getRfc_id() {
        return rfc_id;
    }

    public void setRfc_id(String rfc_id) {
        this.rfc_id = rfc_id;
    }

    public ArrayList<HashMap<String, String>> getRfcImportParameterList() {
        return rfcImportParameterList;
    }

    public void setRfcImportParameterList(ArrayList<HashMap<String, String>> rfcImportParameterList) {
        this.rfcImportParameterList = rfcImportParameterList;
    }

    public ArrayList<HashMap<String, String>> getRfcExportParameterList() {
        return rfcExportParameterList;
    }

    public void setRfcExportParameterList(ArrayList<HashMap<String, String>> rfcExportParameterList) {
        this.rfcExportParameterList = rfcExportParameterList;
    }

    public ArrayList<HashMap<String, String>> getRfcChangingParameterList() {
        return rfcChangingParameterList;
    }

    public void setRfcChangingParameterList(ArrayList<HashMap<String, String>> rfcChangingParameterList) {
        this.rfcChangingParameterList = rfcChangingParameterList;
    }

    public ArrayList<HashMap<String, String>> getRfcTalbeParaMeterList() {
        return rfcTalbeParaMeterList;
    }

    public void setRfcTalbeParaMeterList(ArrayList<HashMap<String, String>> rfcTalbeParaMeterList) {
        this.rfcTalbeParaMeterList = rfcTalbeParaMeterList;
    }

    public ArrayList<HashMap<String, String>> getRfCExceptionList() {
        return rfCExceptionList;
    }

    public void setRfCExceptionList(ArrayList<HashMap<String, String>> rfCExceptionList) {
        this.rfCExceptionList = rfCExceptionList;
    }

}
