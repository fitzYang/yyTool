/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.exception;

/**
 * <p> 数据库异常类 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class DaoException extends RuntimeException {
    private static final long serialVersionUID = -1035788794356688199L;

    public DaoException() {
        super();
    }

    public DaoException(String message) {
        super(message);
    }

    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoException(Throwable cause) {
        super(cause);
    }
}
