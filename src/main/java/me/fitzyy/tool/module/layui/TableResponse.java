/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.module.layui;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import lombok.Data;
import me.fitzyy.tool.StringPool;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 16:09  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
@Data
public class TableResponse<D> implements Serializable {
    private static final long serialVersionUID = 7177259561349171228L;

    /**
     * 状态码，0代表成功，其它失败
     */
    private int code;


    /**
     * 状态信息，一般可为空
     */
    private String msg;


    /**
     * 数据总量
     */
    private int count;


    /**
     * 数据，字段是任意的。
     */
    private List<D> data;


    /**
     * 给定总条数和总的记录数据 构建响应对象
     *
     * @param total   总条数
     * @param records 当前页的数量
     * @param <T>     数据类型
     * @return 响应对象
     */
    public static <T> TableResponse<T> ok(int total, List<T> records) {
        final TableResponse<T> tableResponse = new TableResponse<>();
        tableResponse.setData(records);
        tableResponse.setCount(total);
        tableResponse.setCode(0);
        tableResponse.setMsg(StringPool.EMPTY);
        return tableResponse;
    }

    /**
     * 构建错误信息
     *
     * @param message 错误提示
     * @param <T>     数据类型
     * @return 错误响应信息
     */
    public static <T> TableResponse<T> error(String message) {
        final TableResponse<T> tableResponse = new TableResponse<>();
        tableResponse.setData(Collections.emptyList());
        tableResponse.setCount(0);
        tableResponse.setCode(1);
        tableResponse.setMsg(message);
        return tableResponse;
    }
}
