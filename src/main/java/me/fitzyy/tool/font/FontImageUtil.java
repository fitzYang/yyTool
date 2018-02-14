/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.font;

import com.google.common.base.Preconditions;

import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Optional;

import javax.imageio.ImageIO;

/**
 * <p> 字体图片工具 </p>
 *
 * <p> Created At 2018-02-14 16:17  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class FontImageUtil {

    private FontImageUtil() {
    }


    /**
     * 根据指定字体和大小颜色等信息，生成指定大小的图片信息
     *
     * @param fontParam 字体参数
     * @param content   文字内容
     * @param imagePath 图片路径
     * @throws FileNotFoundException 如果字体不存在，则会抛出这个错误
     */
    public static void fontImage(FontParam fontParam, String content, String imagePath)
            throws FileNotFoundException {
        Preconditions.checkArgument(StringUtils.isEmpty(content), "content can't not null or empty.");
        Preconditions.checkArgument(StringUtils.isEmpty(imagePath), "imagePath can't not null or empty.");
        BufferedImage bufferedImage;

        final String fontFamily = fontParam.getFamily();
        final float fontSize = fontParam.getSize();
        final Optional<Font> fontOpt = LoadFontUtil.loadFont(fontFamily, fontSize);
        if (!fontOpt.isPresent()) {
            throw new FileNotFoundException("The font " + fontFamily + " not found.");
        }

        final Font font = fontOpt.get();

        bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = bufferedImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        FontMetrics fontMetrics = graphics.getFontMetrics(font);
        final int height = fontMetrics.getHeight();

        // 计算文本占用的宽度
        int strPixelWidth = fontMetrics.stringWidth(content);

        bufferedImage = new BufferedImage(strPixelWidth, height, BufferedImage.TYPE_INT_ARGB);
        final Graphics2D g = bufferedImage.createGraphics();
        g.setFont(font);

        // 抗锯齿
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        final String color = fontParam.getColor();
        g.setColor(Color.decode(color));
        g.drawString(content, 0, fontMetrics.getMaxAscent());


        try {
            File imageFile = new File(imagePath);
            ImageIO.write(bufferedImage, "png", imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            g.dispose();
        }
    }
}
