/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.servlet.xss;


import java.util.regex.Pattern;

import me.fitzyy.tool.StringPool;

/**
 * <p> Web防火墙工具类 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings("unused")
public class WafUtil {


    private static final Pattern SCRIPT_REG_PATTERN = Pattern.compile("<script>(.*?)</script>", Pattern.CASE_INSENSITIVE);
    private static final Pattern SCRIPT_END_PATTERN = Pattern.compile("</script>", Pattern.CASE_INSENSITIVE);
    private static final Pattern SCRIPT_PATTERN     = Pattern.compile("<script(.*?)>", Pattern.CASE_INSENSITIVE
            | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern EVAL_PATTERN       = Pattern.compile("eval\\((.*?)\\)", Pattern.CASE_INSENSITIVE
            | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern EXPRESSION_PATTERN = Pattern.compile("expression\\((.*?)\\)", Pattern.CASE_INSENSITIVE
            | Pattern.MULTILINE | Pattern.DOTALL);
    private static final Pattern JAVASCRIPT_PATTERN = Pattern.compile("javascript:", Pattern.CASE_INSENSITIVE);
    private static final Pattern VBSCRIPT_PATTERN   = Pattern.compile("vbscript:", Pattern.CASE_INSENSITIVE);
    private static final Pattern ONLOAD_PATTERN     = Pattern.compile("onload(.*?)=", Pattern.CASE_INSENSITIVE
            | Pattern.MULTILINE | Pattern.DOTALL);

    /**
     * 过滤XSS脚本内容
     *
     * @param value 待处理内容
     * @return 过滤后的内容
     */
    public static String stripXSS(String value) {
        String rlt = null;

        if (null != value) {
            // NOTE: It's highly recommended to use the ESAPI library and uncomment the following line to
            // avoid encoded attacks.
            // value = ESAPI.encoder().canonicalize(value);

            // Avoid null characters
            rlt = value.replaceAll(StringPool.EMPTY, StringPool.EMPTY);

            // Avoid anything between script tags
            rlt = SCRIPT_REG_PATTERN.matcher(rlt).replaceAll(StringPool.EMPTY);

            // Avoid anything in a src='...' type of expression
            /*scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\'(.*?)\\\'", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL);
			rlt = scriptPattern.matcher(rlt).replaceAll(StringPool.EMPTY);

			scriptPattern = Pattern.compile("src[\r\n]*=[\r\n]*\\\"(.*?)\\\"", Pattern.CASE_INSENSITIVE
					| Pattern.MULTILINE | Pattern.DOTALL);
			rlt = scriptPattern.matcher(rlt).replaceAll(StringPool.EMPTY);*/

            // Remove any lonesome </script> tag
            rlt = SCRIPT_END_PATTERN.matcher(rlt).replaceAll(StringPool.EMPTY);

            // Remove any lonesome <script ...> tag
            rlt = SCRIPT_PATTERN.matcher(rlt).replaceAll(StringPool.EMPTY);

            // Avoid eval(...) expressions
            rlt = EVAL_PATTERN.matcher(rlt).replaceAll(StringPool.EMPTY);

            // Avoid expression(...) expressions
            rlt = EXPRESSION_PATTERN.matcher(rlt).replaceAll(StringPool.EMPTY);

            // Avoid javascript:... expressions
            rlt = JAVASCRIPT_PATTERN.matcher(rlt).replaceAll(StringPool.EMPTY);

            // Avoid vbscript:... expressions
            rlt = VBSCRIPT_PATTERN.matcher(rlt).replaceAll(StringPool.EMPTY);

            // Avoid onload= expressions
            rlt = ONLOAD_PATTERN.matcher(rlt).replaceAll(StringPool.EMPTY);
        }

        return rlt;
    }

    /**
     * 过滤SQL注入内容
     *
     * @param value 待处理内容
     * @return 过滤后的内容
     */
    public static String stripSqlInjection(String value) {
        // value.replaceAll("('.+--)|(--)|(\\|)|(%7C)", "");
        return (null == value) ? null : value.replaceAll("('.+--)|(--)|(%7C)", StringPool.EMPTY);
    }

    /**
     * 过滤SQL/XSS注入内容
     *
     * @param value 待处理内容
     * @return 过滤后的内容
     */
    public static String stripSqlXSS(String value) {
        return stripXSS(stripSqlInjection(value));
    }
}
