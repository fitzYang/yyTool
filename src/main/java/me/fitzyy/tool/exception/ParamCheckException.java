/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.exception;


import me.fitzyy.tool.error.ErrorCode;

/**
 * <p> 参数验证异常 </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.7
 */
public class ParamCheckException extends RuntimeException {

    private static final long serialVersionUID = 7842501935220560306L;

    private final ErrorCode code;


    public ParamCheckException(ErrorCode code) {
        super();
        this.code = code;
    }


    public ParamCheckException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public ParamCheckException(ErrorCode code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ParamCheckException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }


    public ErrorCode getCode() {
        return code;
    }
}
