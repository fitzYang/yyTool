/*
 * Copyright (c) 2018. Fitzyy
 * All Rights Reserved
 */

package me.fitzyy.tool.io;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 21:49  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class PdfUtilTest {
    private static String pdfFilePath;

    @Before
    public void setUp() throws Exception {
        pdfFilePath = "";
    }

    @Test
    public void convertImage() {

        try {
            PdfUtil.convertImage("/Users/FitzYang/iDevelop/RDC/yyTool/src/test/resources/pdf/实用开发规范.pdf");
        } catch (IOException | PDFException | InterruptedException | PDFSecurityException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void convertImage2() {

        try {
            PdfUtil.convertImage("/Users/FitzYang/iDevelop/RDC/yyTool/src/test/resources/pdf/实用开发规范.pdf", 2);
        } catch (IOException | PDFException | InterruptedException | PDFSecurityException e) {
            e.printStackTrace();
        }
    }
}