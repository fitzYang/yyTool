/*
 * Copyright (c) 2018. Fitzyy
 * All Rights Reserved
 */

package me.fitzyy.tool.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * <p> </p>
 *
 * <p> Created At 2018-02-14 15:00  </p>
 *
 * @author FitzYang
 * @version 1.0
 * @since JDK 1.8
 */
public class PinYinUtilTest {

    @Test
    public void domain() {
        final String domain = PinYinUtil.domain("杨");
        Assert.assertEquals(domain, "yang");
    }

    @Test
    public void firstCharDomain() {
    }

    @Test
    public void firstCharDomainToUpperCase() {

        final String domain = PinYinUtil.firstCharDomainToUpperCase("杨");
        Assert.assertEquals(domain, "Y");
    }

    @Test
    public void firstCharDomainToLowerCase() {

        final String domain = PinYinUtil.firstCharDomainToLowerCase("杨");
        Assert.assertEquals(domain, "y");
    }

    @Test
    public void getPYIndexStr() {
    }
}