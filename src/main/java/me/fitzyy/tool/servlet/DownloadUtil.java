/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.servlet;

import com.google.common.net.HttpHeaders;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.lang.Func;
import me.fitzyy.tool.StringPool;

/**
 * <p> 文件下载工具类 </p>
 *
 * <p> Created At 2018-02-14 16:33  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class DownloadUtil {

    /**
     * DownloadUtil's Logger
     */
    private static final Logger LOGGER        = LoggerFactory.getLogger(DownloadUtil.class);
    private static final String FIREFOX_AGENT = "firefox";
    private static final String SAFARI_AGENT  = "safari";

    public static void downloadNothind(HttpServletResponse response, String msg) {

        response.setContentType("text/html;charset=utf-8");
        response.setDateHeader(HttpHeaders.EXPIRES, 0);
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setHeader(HttpHeaders.PRAGMA, "no-cache");
        try {
            final PrintWriter printWriter = response.getWriter();
            printWriter.write(msg);
            printWriter.close();
        } catch (IOException e) {
            LOGGER.error("respone txt nothing" + e);
        }
    }


    /**
     * @param response Http Servlet Response
     * @param request  Http Request
     * @param title    普通的下载文件名
     * @param osFunc   处理文件流到Servlet输出流
     * @throws IOException 文件处理失败
     */
    public static void downloadFile(
            HttpServletResponse response,
            HttpServletRequest request,
            String title, Func<OutputStream, Void> osFunc
    ) throws IOException {
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        String exportTitle = DateUtil.format(new Date(), DatePattern.NORM_DATE_FORMAT) + title + ".xls";
        OutputStream os = null;
        try {
            String agent = request.getHeader(HttpHeaders.USER_AGENT);
            String enableFileName = parseAgentDownloadFIleName(exportTitle, agent);
            response.addHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;fileName=" + enableFileName);
            os = response.getOutputStream();
            osFunc.call(os);
            os.flush();
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("file name encoding has error!" + e);
        } finally {
            IoUtil.close(os);
        }
    }


    /**
     * 通过userAgent 解析 下载文件
     *
     * @param exportTitle 下载文件名
     * @param agent       request userAgent
     * @return 解析后的文件名称
     */
    private static String parseAgentDownloadFIleName(String exportTitle, String agent) {
        String enableFileName;
        try {
            if (agent != null && StringUtils.containsIgnoreCase(agent, FIREFOX_AGENT)) {
                //火狐浏览器下载附件乱码
                final String firefoxTitleName = Base64.encode(exportTitle.getBytes(StringPool.UTF_8));
                enableFileName = "=?UTF-8?B?" + firefoxTitleName + "?=";

            } else if (agent != null && StringUtils.containsIgnoreCase(agent, SAFARI_AGENT)) {
                //safari浏览器下载附件
                enableFileName = new String(exportTitle.getBytes(StringPool.UTF_8), "ISO-8859-1");
            } else {
                //其他浏览器下载附件
                enableFileName = URLEncoder.encode(exportTitle, StringPool.UTF_8);
            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.error("生成下载文件名称处理解析失败，文件名称为 {} ", exportTitle, e);

            enableFileName = exportTitle;
        }
        return enableFileName;
    }
}
