/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.lang;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * <p>
 * .
 * </p>
 *
 * @author walter yang
 * @version 1.0 2013-10-30 10:54 AM
 * @since JDK 1.5
 */
@SuppressWarnings("unused")
public class ExceptionUtil {

    /**
     * 将CheckedException转换为UncheckedException.
     */
    public static RuntimeException unchecked(Exception e) {
        if (e instanceof RuntimeException) {
            return (RuntimeException) e;
        } else {
            return new RuntimeException(e);
        }
    }

    /**
     * 将ErrorStack转化为String.
     */
    public static String getStackTraceAsString(Exception e) {
        StringWriter stringWriter = new StringWriter();
        e.printStackTrace(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    /**
     * 判断异常是否由某些底层的异常引起.
     */
    public static boolean isCausedBy(Exception ex, Class<? extends Exception>... causeExceptionClasses) {
        Throwable cause = ex.getCause();
        while (cause != null) {
            for (Class<? extends Exception> causeClass : causeExceptionClasses) {
                if (causeClass.isInstance(cause)) {
                    return true;
                }
            }
            cause = cause.getCause();
        }
        return false;
    }


    /**
     * Build a message for the given base message and its cause.
     */
    public static String buildMessage(String message, Throwable cause) {
        if (cause != null) {
            cause = getRootCause(cause);
            StringBuilder buf = new StringBuilder();
            if (message != null) {
                buf.append(message).append("; ");
            }
            buf.append("<--- ").append(cause).append(" --->");
            return buf.toString();
        } else {
            return message;
        }
    }


    /**
     * Introspects the <code>Throwable</code> to obtain the root cause.
     * <p/>
     * This method walks through the exception chain to the last element,
     * "root" of the tree, and returns that exception. If no root cause found
     * returns provided throwable.
     */
    public static Throwable getRootCause(Throwable throwable) {
        Throwable cause = throwable.getCause();
        if (cause == null) {
            return throwable;
        }
        throwable = cause;
        while ((throwable = throwable.getCause()) != null) {
            cause = throwable;
        }
        return cause;
    }

}
