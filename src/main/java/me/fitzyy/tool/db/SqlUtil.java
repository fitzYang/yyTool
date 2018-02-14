/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.db;


import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.StringTokenizer;

import cn.hutool.core.util.StrUtil;
import me.fitzyy.tool.StringPool;


/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class SqlUtil {


    /**
     * 功能描述: 生成sql占位符 ?,?,?
     *
     * @return String    返回类型
     */
    public static String sqlHolder(int size) {
        String[] paras = new String[size];
        Arrays.fill(paras, "?");
        return StringUtils.join(paras, ',');
    }


    private String delSQlString(String sql) {
        StringBuilder delSql = new StringBuilder("IN (");
        StringTokenizer tokenizer = new StringTokenizer(sql, StringPool.PIPE);

        // 标记本身等于分隔符的特殊情况
        delSql.append(tokenizer.nextToken());
        while (tokenizer.hasMoreTokens()) {
            delSql.append(tokenizer.nextToken()).append(StringPool.COMMA);
        }
        delSql = new StringBuilder(delSql.substring(0, delSql.length() - 1) + StringPool.RIGHT_BRACKET);
        return delSql.toString();
    }

    private String delNewSQlString(String sql) {
        return StrUtil.format("IN ({})", sql.replace('|', ','));
    }
}
