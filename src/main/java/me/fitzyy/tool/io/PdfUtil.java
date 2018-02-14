/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.io;

import com.google.common.io.Files;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.PDimension;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ImageUtil;
import me.fitzyy.tool.StringPool;

/**
 * <p> PDF 工具类 </p>
 *
 * <p> Created At 2018-02-14 21:22  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class PdfUtil {


    /**
     * ICEPDF转png
     *
     * @param pdfFilePath pdf文件
     * @return 返回png文件名
     * @throws IOException          文件操作异常
     * @throws PDFException         PDF读取操作异常
     * @throws PDFSecurityException PDF文件权限异常
     * @throws InterruptedException 读取图片转换异常
     */
    public static String[] convertImage(String pdfFilePath)
            throws IOException, PDFException, PDFSecurityException, InterruptedException {
        return convertImage(pdfFilePath, 0);
    }


    /**
     * ICEPDF转png
     *
     * @param pdfFilePath pdf文件
     * @param pageNum     转换页码（0代表所有页）
     * @return 返回png文件名
     * @throws IOException          文件操作异常
     * @throws PDFException         PDF读取操作异常
     * @throws PDFSecurityException PDF文件权限异常
     * @throws InterruptedException 读取图片转换异常
     */
    public static String[] convertImage(String pdfFilePath, int pageNum)
            throws IOException, PDFException, PDFSecurityException, InterruptedException {
        final String pdfFileName = Files.getNameWithoutExtension(pdfFilePath);
        final Document document = new Document();
        document.setFile(pdfFilePath);
        //缩放比例
        float scale = 2.5f;
        //旋转角度
        float rotation = 0f;

        String[] fileNameArray;

        // pdf 所在目录
        final String pdfFolderPath = FilePathUtil.getParentPath(pdfFilePath);

        final String pdfPngFolder = FilePathUtil.contact(pdfFolderPath, pdfFileName);
        File target = new File(pdfPngFolder);
        if (!target.exists()) {
            FileUtil.mkdir(pdfPngFolder);
        }


        if (pageNum == 0) {
            pageNum = document.getNumberOfPages();
            fileNameArray = new String[pageNum];
            for (int i = 0; i < pageNum; i++) {

                Page page = document.getPageTree().getPage(i);
                page.init();

                PDimension sz = page.getSize(Page.BOUNDARY_CROPBOX, rotation, scale);
                int pageWidth = (int) sz.getWidth();
                int pageHeight = (int) sz.getHeight();

                BufferedImage image = new BufferedImage(pageWidth, pageHeight, BufferedImage.TYPE_INT_RGB);
                Graphics g = image.createGraphics();

                page.paint(g, GraphicsRenderingHints.PRINT, Page.BOUNDARY_CROPBOX, rotation, scale);
                g.dispose();

                String imgName = (i + 1) + StringPool.DOT + ImageUtil.IMAGE_TYPE_JPG;
                final File imageName = new File(pdfPngFolder + imgName);
                ImageIO.write(image, ImageUtil.IMAGE_TYPE_JPG, imageName);
                image.flush();
                fileNameArray[i] = imgName;
            }
        } else {
            fileNameArray = new String[1];
            Page page = document.getPageTree().getPage(pageNum);
            page.init();

            PDimension sz = page.getSize(Page.BOUNDARY_CROPBOX, rotation, scale);
            int pageWidth = (int) sz.getWidth();
            int pageHeight = (int) sz.getHeight();

            BufferedImage image = new BufferedImage(pageWidth, pageHeight, BufferedImage.TYPE_INT_RGB);
            Graphics g = image.createGraphics();

            page.paint(g, GraphicsRenderingHints.PRINT, Page.BOUNDARY_CROPBOX, rotation, scale);
            g.dispose();
            String imgName = pageNum + StringPool.DOT + ImageUtil.IMAGE_TYPE_JPG;
            ImageIO.write(image, ImageUtil.IMAGE_TYPE_JPG, new File(pdfPngFolder + imgName));
            image.flush();
            fileNameArray[0] = imgName;
        }
        document.dispose();
        return fileNameArray;
    }
}
