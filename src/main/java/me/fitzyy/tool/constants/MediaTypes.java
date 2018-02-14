/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.constants;

/**
 * <p> Jax-RS和Spring的MediaType没有UTF-8的版本;
 *
 * Google的MediaType必须再调用toString()函数而不是常量，不能用于Restful方法的annotation
 * </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.7
 */
public interface MediaTypes {


    String APPLICATION_XML       = "application/xml";
    String APPLICATION_XML_UTF_8 = "application/xml; charset=UTF-8";

    String JSON       = "application/json";
    String JSON_UTF_8 = "application/json; charset=UTF-8";

    String JAVASCRIPT       = "application/javascript";
    String JAVASCRIPT_UTF_8 = "application/javascript; charset=UTF-8";

    String APPLICATION_XHTML_XML       = "application/xhtml+xml";
    String APPLICATION_XHTML_XML_UTF_8 = "application/xhtml+xml; charset=UTF-8";

    String TEXT_PLAIN       = "text/plain";
    String TEXT_PLAIN_UTF_8 = "text/plain; charset=UTF-8";

    String TEXT_XML       = "text/xml";
    String TEXT_XML_UTF_8 = "text/xml; charset=UTF-8";

    String TEXT_HTML       = "text/html";
    String TEXT_HTML_UTF_8 = "text/html; charset=UTF-8";
}
