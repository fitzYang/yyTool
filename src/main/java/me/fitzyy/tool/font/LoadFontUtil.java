/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.font;

import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import cn.hutool.core.io.IoUtil;
import me.fitzyy.tool.StringPool;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 16:13  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class LoadFontUtil {

    /**
     * LoadFontUtil's Logger
     */
    private static final Logger            LOGGER          = LoggerFactory.getLogger(LoadFontUtil.class);
    /**
     * 已经加载的字体
     */
    private static final Map<String, Font> EXIST_LOAD_FONT = Maps.newHashMap();
    /**
     * 字体存放路径
     */
    private static String FONT_DIR;

    private LoadFontUtil() {
    }

    public static void setFontDir(String fontDir) {
        FONT_DIR = fontDir;
    }

    /**
     * 加载字体，如果已经缓存了，则直接返回缓存字体
     *
     * @param fontFamily 字体名称
     * @param fontSize   字体大小
     * @return 字体
     */
    public static Optional<Font> loadFont(String fontFamily, float fontSize) {
        if (StringUtils.isEmpty(FONT_DIR)) {
            throw new IllegalArgumentException("please setFontDir with font dir.");
        }
        final Font font = EXIST_LOAD_FONT.get(fontFamily + StringPool.UNDERSCORE + fontSize);
        if (font == null) {
            File fontFile = new File(FONT_DIR + fontFamily);
            FileInputStream fontStream = null;

            try {
                fontStream = new FileInputStream(fontFile);
                Font dynamicFont = Font.createFont(Font.TRUETYPE_FONT, fontStream);

                Font dynamicFontPt = dynamicFont.deriveFont(fontSize);

                EXIST_LOAD_FONT.put(fontFamily + StringPool.UNDERSCORE + fontSize, dynamicFontPt);
                return Optional.of(dynamicFontPt);
            } catch (FontFormatException | IOException e) {
                LOGGER.error("加载字体出现错误！字体名称 {}", fontFamily, e);
            } finally {
                IoUtil.close(fontStream);
            }
        } else {
            return Optional.of(font);
        }
        return Optional.empty();
    }
}
