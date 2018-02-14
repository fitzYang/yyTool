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
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class BankCardUtilTest {


    @Test
    public void testCheck() throws Exception {
        final boolean bankCard = BankCardUtil.checkBankCard("6228450668034664775");
        Assert.assertFalse(bankCard);
    }

    @Test
    public void testGetName() throws Exception {

        final String nameOfBank = BankCardUtil.getNameOfBank("622845");
        System.out.println("nameOfBank = " + nameOfBank);
        Assert.assertEquals("农业银行·金穗通宝卡(银联卡)", nameOfBank);
    }
}