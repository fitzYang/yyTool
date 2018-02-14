/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap;

import com.sap.conn.jco.JCoContext;
import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoRepository;
import com.sap.conn.jco.ext.Environment;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import me.fitzyy.tool.error.BasisCode;
import me.fitzyy.tool.exception.BussinessException;


/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class SapConnection {

    private static final Logger LOGGER = LoggerFactory.getLogger(SapConnection.class);

    private JCoRepository  repos;
    private JCoDestination dest;


    public SapConnection(SapProp sapProp) {
        DestinationDataProvider myProvider = DestinationDataProvider.getInstance();
        if (!Environment.isDestinationDataProviderRegistered()) {
            Environment.registerDestinationDataProvider(myProvider);
        }
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("the sap connection is : {}", sapProp);
        }
        try {
            myProvider.addSystem(sapProp);
            dest = JCoDestinationManager.getDestination(sapProp.getName());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("Attributes: {}", dest.getAttributes());
            }
            repos = dest.getRepository();
        } catch (Exception e) {
            LOGGER.info("====connection error!!!====", e);
            throw new BussinessException(e, BasisCode.SAP_CONNECTION_ERROR);
        }
    }


    /**
     * 获取 SAP函数信息
     *
     * @param functionStr SAP函数名称
     */
    public JCoFunction getFunction(String functionStr) {
        JCoFunction function;
        try {
            function = repos.getFunction(functionStr);
        } catch (Exception e) {
            LOGGER.error("Problem retrieving JCoFunction object.", e);
            throw new BussinessException(e, BasisCode.SAP_FUNC_DEFINE_ERROR);
        }
        if (function == null) {
            LOGGER.warn("Not possible to receive function.");
        }
        return function;
    }

    /**
     * 执行SAP函数
     *
     * @param function sap函数
     */
    public void execute(JCoFunction function) {
        try {
            JCoContext.begin(dest);
            function.execute(dest);
            // repos.clear(); //清楚缓存
        } catch (JCoException e) {
            LOGGER.error("Problem execute JCoFunction.", e);
            throw new BussinessException(e, BasisCode.SAP_FUNC_EXEC_ERROR);
        } finally {
            try {
                JCoContext.end(dest);
            } catch (JCoException e) {
                LOGGER.error("Problem end of execute JCoFunction ", e);
            }
        }
    }
}
