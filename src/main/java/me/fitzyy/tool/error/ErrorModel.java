/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.error;

import org.apache.commons.lang3.StringUtils;

/**
 * <p> 错误信息Model </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings("unused")
public class ErrorModel {

    /**
     * 错误代码
     */
    private final int code;

    /**
     * 消息提醒
     */
    private final String message;

    private ErrorModel(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Builder builder() {
        return new Builder();
    }


    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static class Builder {
        private ErrorCode errorCode;
        private String message = StringUtils.EMPTY;

        Builder() {
        }

        public Builder setCode(ErrorCode errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public ErrorModel createErrorModel() {
            return new ErrorModel(errorCode.getCode(), message);
        }
    }
}
