/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.datetime;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 13:57  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class TimeUtil {
    private TimeUtil() {
    }

    /**
     * 获取当前时间 单位：s
     *
     * @return 当前时间
     */
    public static int unixTime() {
        return (int) (System.currentTimeMillis() / 1000);
    }

    /**
     * 通过以s为单位的当前时间返回日期
     *
     * @param unix 以s为单位的时间
     * @return unix对应的日期
     */
    public static Date dateTime(int unix) {
        return new Date(unix * 1000L);
    }

    /**
     * 通过日期返回对应的时间 单位s
     *
     * @param date 日期
     * @return 对应的以s为单位的时间
     */
    public static int unixTime(Date date) {
        return date == null ? 0 : (int) (date.getTime() / 1000);
    }

    /**
     * 通过日期返回对应的时间 单位s
     *
     * @param dateTime 日期
     * @return 对应的以s为单位的时间
     */
    public static int unixTime(DateTime dateTime) {
        return dateTime == null ? 0 : (int) (dateTime.getMillis() / 1000);
    }


    /**
     * 获取某个时间的 一天 的开始时间和结束时间
     *
     * @param time 时间
     * @return 开始和结束时间 unixTime
     */
    public static int[] dayOfMinMaxUnitTime(DateTime time) {
        final DateTime.Property millisOfDay = time.millisOfDay();
        final DateTime maximumValue = millisOfDay.withMaximumValue();
        final DateTime minimumValue = millisOfDay.withMinimumValue();
        return new int[]{unixTime(minimumValue), unixTime(maximumValue)};
    }

    /**
     * 获取某个时间的 所在月份的 的开始时间和结束时间
     *
     * @param month 月
     * @return 开始和结束时间 unixTime
     */
    public static int[] monthOfMinMaxUnitTime(DateTime month) {
        final DateTime.Property monthOfYear = month.monthOfYear();
        final DateTime maximumValue = monthOfYear.withMaximumValue();
        final DateTime minimumValue = monthOfYear.withMinimumValue();
        return new int[]{unixTime(minimumValue), unixTime(maximumValue)};
    }

    /**
     * 获取长整型当前时间 单位：s
     *
     * @return 以s为单位的长整型当前时间
     */
    public static long unixTimeLong() {
        return (System.currentTimeMillis() / 1000);
    }
}
