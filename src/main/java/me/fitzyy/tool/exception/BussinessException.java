/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.exception;


import me.fitzyy.tool.error.ErrorCode;

/**
 * <p> 业务异常</p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings("unused")
public class BussinessException extends RuntimeException {
    private static final long serialVersionUID = -6203833293548878526L;

    private final ErrorCode errorCode;


    public BussinessException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BussinessException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public BussinessException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public BussinessException(Throwable cause, ErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
