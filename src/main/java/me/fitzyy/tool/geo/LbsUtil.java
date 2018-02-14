/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.geo;

import cn.hutool.core.util.NumberUtil;

/**
 * <p> LBS操作相关工具类</p>
 *
 * <p> Created At 2018-02-14 15:54  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class LbsUtil {

    private static final double EARTH_RADIUS = 6378137;

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离，保留两位小数
     *
     * @param lat1 纬度
     * @param lng1 经度
     * @param lat2 纬度
     * @param lng2 经度
     * @return 距离：单位为千米
     */
    public static double distanceOfTwoPoints(double lat1, double lng1,
                                             double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / (double) 10000;
        double res = s / 1000;


        return NumberUtil.round(res, 2).doubleValue();
    }

    /**
     * 根据两点间经纬度坐标（double值），计算两点间距离
     *
     * @param lat1 纬度
     * @param lng1 经度
     * @param lat2 纬度
     * @param lng2 经度
     * @return 距离：单位为米
     */
    public static double getDistance(double lng1, double lat1,
                                     double lng2, double lat2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
