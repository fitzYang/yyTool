/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.module.ztree;

import java.util.List;

import cn.hutool.core.collection.CollectionUtil;
import me.fitzyy.tool.StringPool;
import me.fitzyy.tool.lang.JsonUtil;
import me.fitzyy.tool.module.ztree.domain.ZtreeVO;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 15:48  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class ZtreeUtil {

    private ZtreeUtil() {
    }


    /**
     * 将存放json实体类的List转换成JSON数据
     *
     * @param datalist 存放json实体类的List List<ZTreeData> datalist
     * @return String 字符串形式的JSON数据
     */
    static public String ztreeData(List<ZtreeVO> datalist) {
        if (CollectionUtil.isEmpty(datalist)) {
            return StringPool.EMPTY_JSON_ARRAY;
        }
        return JsonUtil.toJsonWithoutRef(datalist);
    }
}
