/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.module.datatables;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import java.util.List;

import me.fitzyy.tool.module.datatables.domain.Column;
import me.fitzyy.tool.module.datatables.domain.DataTablesInput;
import me.fitzyy.tool.module.datatables.domain.DataTablesOutput;

/**
 * <p>
 * DataTables 工具类
 * </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings("unused")
public class DataTablesUtils {


    /**
     * 通过jQuery Tables 传入的参数，解析成可理解的分页POJO
     *
     * @param input the {@link DataTablesInput} mapped from the Ajax request
     * @return a {@link org.springframework.data.domain.Pageable}, must not be {@literal null}.
     */
    public static <Q> DataTablesPagination getPageable(DataTablesInput input) {
        if (input.getLength() == -1) {
            input.setStart(0);
            input.setLength(Integer.MAX_VALUE);
        }
        final int start = MoreObjects.firstNonNull(input.getStart(), 0);
        int current = start / input.getLength();
        final Pagination pagination = new Pagination(current + 1, input.getLength());
        List<Order> orders = Lists.newArrayList();
        for (me.fitzyy.tool.module.datatables.domain.Order order : input.getOrder()) {
            Column column = input.getColumns().get(order.getColumn());
            pagination.setOpenSort(true);
            if (column.getOrderable()) {
                String sortColumn = column.getData();
                if (!pagination.isOpenSort()) {
                    pagination.setOrderByField(sortColumn);
                    pagination.setOpenSort(true);
                    final String dir = order.getDir();
                    if (StringUtils.equalsIgnoreCase(dir, "asc")) {
                        pagination.setAsc(true);
                    }
                }

                Direction sortDirection = Direction.fromString(order.getDir());
                orders.add(new Order(sortDirection, sortColumn));
            }
        }
        final DataTablesPagination dataTablesPagination = new DataTablesPagination();
        dataTablesPagination.setPagination(pagination);
        dataTablesPagination.setOrders(orders);
        return dataTablesPagination;
    }


    /**
     * 将Mybatis查询的分页结果租场成jquerydatatbles可接受的 json返回
     *
     * @param input 原始的jquery datables输入参数
     * @param page  分页结果
     * @param <E>   泛型参数
     * @return jquerydatables 返回的json数据
     */
    public static <E> DataTablesOutput<E> buildOut(DataTablesInput input, Page<E> page) {
        final DataTablesOutput<E> dataTablesOutput = new DataTablesOutput<>();
        dataTablesOutput.setData(page.getRecords());
        dataTablesOutput.setDraw(input.getDraw());
        dataTablesOutput.setRecordsTotal(page.getTotal());
        dataTablesOutput.setRecordsFiltered(page.getTotal());
        return dataTablesOutput;
    }

    /**
     * 将Mybatis查询的分页结果租场成jquerydatatbles可接受的 json返回
     *
     * Note 主要用于搜索后的结果返回。
     *
     * @param input 原始的jquery datables输入参数
     * @param page  分页结果
     * @param total 实际总的数据
     * @param <E>   泛型参数
     * @return jquerydatables 返回的json数据
     */
    public static <E> DataTablesOutput<E> buildOut(DataTablesInput input, Page<E> page, int total) {
        final DataTablesOutput<E> dataTablesOutput = new DataTablesOutput<>();
        dataTablesOutput.setData(page.getRecords());
        dataTablesOutput.setDraw(input.getDraw());
        dataTablesOutput.setRecordsTotal(total);
        dataTablesOutput.setRecordsFiltered(page.getTotal());
        return dataTablesOutput;
    }

}
