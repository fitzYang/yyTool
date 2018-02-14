/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.sap;

import com.google.common.collect.Maps;

import com.sap.conn.jco.ext.DestinationDataEventListener;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.Properties;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings("AlibabaClassNamingShouldBeCamel")
public class DestinationDataProvider implements com.sap.conn.jco.ext.DestinationDataProvider {

    private static Logger log = LoggerFactory.getLogger(DestinationDataProvider.class);
    private static Map<String, Properties> destinations;
    private static Properties              ABAP_AS_properties;
    private static DestinationDataProvider provider = new DestinationDataProvider();

    private DestinationDataProvider() {
        if (provider == null) {
            log.info("Creating MyDestinationDataProvider ...");
            destinations = Maps.newHashMap();
        }
    }

    /**
     * Add new destination
     **/
    public static DestinationDataProvider getInstance() {
        log.info("Creating MyDestinationDataProvider ...");
        return provider;
    }

    public void addSystem(SapProp system) {
        Properties properties = new Properties();
        properties.setProperty(com.sap.conn.jco.ext.DestinationDataProvider.JCO_ASHOST, system.getHost());
        // 系统编号
        properties.setProperty(com.sap.conn.jco.ext.DestinationDataProvider.JCO_SYSNR, system.getSystemNumber());
        // SAP集团
        properties.setProperty(com.sap.conn.jco.ext.DestinationDataProvider.JCO_CLIENT, system.getClient());
        final String r3name = system.getR3name();
        if (StringUtils.isNotEmpty(r3name)) {
            properties.setProperty(com.sap.conn.jco.ext.DestinationDataProvider.JCO_R3NAME, r3name);
        }
        final String saprouter = system.getSaprouter();
        if (StringUtils.isNotEmpty(saprouter)) {
            properties.setProperty(com.sap.conn.jco.ext.DestinationDataProvider.JCO_SAPROUTER, saprouter);
        }
        // 登录语言
        properties.setProperty(com.sap.conn.jco.ext.DestinationDataProvider.JCO_LANG, system.getLanguage());
        // SAP用户名
        properties.setProperty(com.sap.conn.jco.ext.DestinationDataProvider.JCO_USER, system.getUser());
        // 密码
        properties.setProperty(com.sap.conn.jco.ext.DestinationDataProvider.JCO_PASSWD, system.getPassword());
        final String poolCapacity = system.getPoolCapacity();
        if (StringUtils.isNotEmpty(poolCapacity)) {
            // 最大连接数
            properties.setProperty(com.sap.conn.jco.ext.DestinationDataProvider.JCO_POOL_CAPACITY, poolCapacity);
        }
        final String peakLimit = system.getPeakLimit();
        if (StringUtils.isNotEmpty(peakLimit)) {
            // 最大连接线程
            properties.setProperty(com.sap.conn.jco.ext.DestinationDataProvider.JCO_PEAK_LIMIT, peakLimit);
        }

        ABAP_AS_properties = properties;
        this.addDestination(system.getName(), ABAP_AS_properties);
    }

    @Override
    public Properties getDestinationProperties(String destinationName) {
        if (destinations.containsKey(destinationName)) {
            return destinations.get(destinationName);
        } else {
            throw new RuntimeException("Destination " + destinationName + " is not available");
        }
    }

    @Override
    public void setDestinationDataEventListener(DestinationDataEventListener eventListener) {
    }

    @Override
    public boolean supportsEvents() {
        return false;
    }

    private void addDestination(String destinationName, Properties properties) {
        synchronized (destinations) {
            if (destinations.containsKey(destinationName)) {
                destinations.remove(destinationName);
            }
            destinations.put(destinationName, properties);
        }
    }
}
