/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.io;


import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

/**
 * <p>返回当前java进程占用的CPU情况 </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.7
 */
public class MonitorUtil {
    private static OperatingSystemMXBean osbean       = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
    private static RuntimeMXBean         runbean      = (RuntimeMXBean) ManagementFactory.getRuntimeMXBean();
    private static long                  cpuTimes     = 0;
    private static long                  cpuTimeFetch = 0;

    /**
     * 获取CPU利用率
     *
     * @return CPU利用率
     */
    public static double getCpuUsage() {
        long cpuNumber = Runtime.getRuntime().availableProcessors();
        long currentTimeMillis = System.currentTimeMillis();
        int sum = 0;
        long catchTime = 0;
        long current = 0;
        long previous = cpuTimes;
        current = osbean.getProcessCpuTime();
        catchTime = runbean.getUptime();
        sum += (current - previous);
        cpuTimes = current;
        cpuTimeFetch = currentTimeMillis;
        return (double) sum / (double) ((currentTimeMillis - catchTime) * cpuNumber * 1000);
    }
}
