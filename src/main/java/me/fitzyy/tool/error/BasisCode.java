/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.error;

/**
 * <p>基础错误码 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public enum BasisCode implements ErrorCode {
    /**
     * 系统级错误
     */
    SYS_ERROR(1),
    /**
     * 服务器错误
     */
    SERVICE_ERROR(2),
    /**
     * 无法找到
     */
    NOF_FOUND(400),
    /**
     * 请求成功
     */
    OK(200),
    /**
     * 服务端错误
     */
    SERVER_ERROR(500),
    /**
     * 请求超时
     */
    TIMEOUT(504),
    /**
     * 暂不支持
     */
    NOT_SUPPORTED(505),
    /**
     * 拒绝访问
     */
    FORBIDDEN(403),
    /**
     * 数据库错误
     */
    DB_ERROR(599),
    /**
     * JSON数据错误
     */
    JSON_PARAM_ERROR(1001),
    /**
     * 系统配置错误
     */
    SYS_SETTING_ERRRO(1002),

    /**
     * 接口停用
     */
    API_STOP(1003),
    /**
     * 请求TOKEN无权限
     */
    API_TOKEN_SUPPORTED(1004),
    /**
     * 错误的请求TOKEN
     */
    API_TOKEN_ERROR(1005),
    /**
     * 请求TOKEN已过期
     */
    API_TOKEN_EXPIRE(1006),
    /**
     * 请求TOKEN已禁止
     */
    API_TOKEN_BAN(1007),

    /**
     * 请求IP已禁止
     */
    IP_BAN(1008),
    /**
     * 请求IP超过请求次数限制
     */
    IP_LIMIT_TIMES(1009),

    /**
     * SAP链接错误
     */
    SAP_CONNECTION_ERROR(2001),
    /**
     * SAP函数定义错误
     */
    SAP_FUNC_DEFINE_ERROR(2002),
    /**
     * SAP函数执行错误
     */
    SAP_FUNC_EXEC_ERROR(2003);

    private final int code;

    BasisCode(int code) {
        this.code = code;
    }

    @Override
    public int getCode() {
        return this.code;
    }
}
