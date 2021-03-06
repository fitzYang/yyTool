/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.io;

import com.google.common.collect.Maps;

import java.text.DecimalFormat;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.apache.commons.lang3.StringUtils.trimToNull;

/**
 * <p>以易读的方式来解析和显示字节数。 </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.7
 */
public class HumanReadableSize {

    private final static Long              ONE        = 1L;
    private final static Long              ONE_KILO   = 1024L;
    private final static Long              ONE_MEGA   = 1024 * 1024L;
    private final static Long              ONE_GIGA   = 1024 * 1024 * 1024L;
    private final static Long              ONE_TERA   = 1024 * 1024 * 1024 * 1024L;
    private final static Map<String, Long> UNIT_NAMES = Maps.newHashMap();
    private final static Pattern           REGEXP     = Pattern.compile("(\\d+(\\.\\d+)?)\\s*(K|M|G|T)?", Pattern.CASE_INSENSITIVE);

    static {
        UNIT_NAMES.put("B", ONE);
        UNIT_NAMES.put("K", ONE_KILO);
        UNIT_NAMES.put("M", ONE_MEGA);
        UNIT_NAMES.put("G", ONE_GIGA);
        UNIT_NAMES.put("T", ONE_TERA);
    }

    private HumanReadableSize() {
    }

    /**
     * 将易读的字节数转换成真实字节数。 <ul> <li><code>1</code> 转换成 <code>1</code>。</li> <li><code>1K</code> 转换成
     * <code>1024</code>。</li> <li><code>1M</code> 转换成 <code>1048576</code>。</li>
     * <li><code>1G</code> 转换成 <code>1073741824</code>。</li> <li><code>1T</code> 转换成
     * <code>1099511627776</code>。</li> <li>支持小数点，例如：<code>1.5K</code>。</li> </ul>
     */
    public static long parse(String humanReadableSize) {
        return parse(humanReadableSize, (String[]) null);
    }

    /**
     * 将字节数取整，并转换成易读的概要字节数。 <ul> <li><code>1</code>转换成<code>1</code>。</li>
     * <li><code>1024</code>转换成<code>1K</code>。</li> <li><code>1048576</code>转换成<code>1M</code>。</li>
     * <li><code>1073741824</code>转换成<code>1G</code>。</li> <li><code>1099511627776</code>转换成<code>1T</code>。</li>
     * <li>小于<code>0</code>的一律转换成<code>n/a</code>。</li> </ul>
     */
    public static String toHumanReadble(long size) {
        if (size < 0) {
            return "n/a";
        }

        DecimalFormat format = new DecimalFormat("#.##");

        if (size / ONE_TERA > 0) {
            return format.format((double) size / ONE_TERA) + "T";
        }

        if (size / ONE_GIGA > 0) {
            return format.format((double) size / ONE_GIGA) + "G";
        }

        if (size / ONE_MEGA > 0) {
            return format.format((double) size / ONE_MEGA) + "M";
        }

        if (size / ONE_KILO > 0) {
            return format.format((double) size / ONE_KILO) + "K";
        }

        return String.valueOf(size);
    }


    private static long parse(String humanReadableSize, String... nas) {

        if (nas != null) {
            for (String na : nas) {
                if (na != null && na.equalsIgnoreCase(humanReadableSize)) {
                    return -1;
                }
            }
        }

        Matcher matcher = REGEXP.matcher(humanReadableSize);


        double size = Double.parseDouble(matcher.group(1));
        String unit = trimToNull(matcher.group(3));

        if (unit != null) {
            size *= UNIT_NAMES.get(unit.toUpperCase());
        }

        return (long) size;
    }
}
