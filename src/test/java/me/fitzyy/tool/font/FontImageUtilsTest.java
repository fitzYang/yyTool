/*
 * Copyright (c) 2018. Fitzyy
 * All Rights Reserved
 */

package me.fitzyy.tool.font;

import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 16:30  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class FontImageUtilsTest {

    @Before
    public void setUp() throws Exception {
        LoadFontUtil.setFontDir("");
    }

    @Test
    public void fontImage() {
        FontParam fontParam = new FontParam();
        try {
            FontImageUtil.fontImage(fontParam, "YY Java工具类", "");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}