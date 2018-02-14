/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.io;

import com.google.common.base.Strings;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;

import cn.hutool.core.io.FileUtil;

/**
 * <p> 图片工具 </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.7
 */
public class ImageUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ImageUtils.class);

    private ImageUtils() {
    }


    /**
     * 判断文件格式是否为图片
     *
     * @param fileType 文件格式
     * @return 是否为图片
     */
    public static boolean isImage(String fileType) {
        return !Strings.isNullOrEmpty(fileType)
                && (StringUtils.equals(fileType, cn.hutool.core.util.ImageUtil.IMAGE_TYPE_BMP)
                || StringUtils.equals(fileType, cn.hutool.core.util.ImageUtil.IMAGE_TYPE_GIF)
                || StringUtils.equals(fileType, cn.hutool.core.util.ImageUtil.IMAGE_TYPE_JPG)
                || StringUtils.equals(fileType, cn.hutool.core.util.ImageUtil.IMAGE_TYPE_JPEG)
                || StringUtils.equals(fileType, cn.hutool.core.util.ImageUtil.IMAGE_TYPE_PNG));
    }

    /**
     * 获取文件图片宽高
     *
     * @param imgFile 图片宽高
     * @return dimensions of image
     * @throws IOException if the file is not a known image
     */
    public static Optional<Integer[]> dimension(File imgFile) throws IOException {
        if (imgFile == null) {
            return Optional.empty();
        }
        if (!imgFile.exists()) {
            return Optional.empty();
        }
        final String type = FileUtil.getType(imgFile);
        if (Strings.isNullOrEmpty(type)) {
            LOGGER.error("Cannot find the file type!");
            throw new IOException("No extension for file: " + imgFile.getAbsolutePath());
        }
        if (!isImage(type)) {
            LOGGER.error("The file type is {}, not image!", type);
            return Optional.empty();
        }
        Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(type);
        while (iter.hasNext()) {
            ImageReader reader = iter.next();
            try {
                ImageInputStream stream = new FileImageInputStream(imgFile);
                reader.setInput(stream);
                int width = reader.getWidth(reader.getMinIndex());
                int height = reader.getHeight(reader.getMinIndex());
                return Optional.of(new Integer[]{width, height});
            } catch (IOException e) {
                LOGGER.warn("Error reading: " + imgFile.getAbsolutePath(), e);
            } finally {
                reader.dispose();
            }
        }

        throw new IOException("Not a known image file: " + imgFile.getAbsolutePath());
    }
}
