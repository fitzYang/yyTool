/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.module.datatables;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;

import org.springframework.data.domain.Sort;

import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p> </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@Data
@NoArgsConstructor
public class DataTablesPagination {

    @NotNull
    private Pagination pagination;


    private List<Sort.Order> orders;
}
