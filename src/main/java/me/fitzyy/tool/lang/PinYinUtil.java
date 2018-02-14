/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.lang;

import com.google.common.base.Strings;

import com.github.stuxuhai.jpinyin.PinyinException;
import com.github.stuxuhai.jpinyin.PinyinHelper;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

import cn.hutool.core.util.StrUtil;
import me.fitzyy.tool.StringPool;

import static com.github.stuxuhai.jpinyin.PinyinFormat.WITHOUT_TONE;

/**
 * <p> 拼音获取 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class PinYinUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PinYinUtil.class);

    public static String domain(String cnName) {
        try {
            return PinyinHelper.convertToPinyinString(cnName, StringPool.EMPTY, WITHOUT_TONE);
        } catch (PinyinException e) {
            LOGGER.error("Domain Function has error!", e);
            return StringPool.EMPTY;
        }
    }


    public static String firstCharDomain(String cnName) {

        final String domain;
        try {
            domain = PinyinHelper.convertToPinyinString(cnName, StringPool.COMMA, WITHOUT_TONE);
        } catch (PinyinException e) {
            LOGGER.error("Domain Function has error!", e);
            return StringPool.EMPTY;
        }
        if (Strings.isNullOrEmpty(domain)) {
            return StringPool.EMPTY;
        }
        final String[] namesPinyin = StrUtil.split(domain, StringPool.COMMA);
        StringBuilder firstChatDominas = new StringBuilder();
        for (String namePinyin : namesPinyin) {
            firstChatDominas.append(StringUtils.left(namePinyin, 1));
        }
        return firstChatDominas.toString();
    }

    public static String firstCharDomainToUpperCase(String cnName) {
        String domain = domain(cnName);
        return StringUtils.upperCase(StringUtils.left(domain, 1));
    }

    public static String firstCharDomainToLowerCase(String cnName) {
        String domain = domain(cnName);
        return StringUtils.lowerCase(StringUtils.left(domain, 1));
    }

    /**
     * 返回首字母
     */
    public static String getPYIndexStr(String strChinese, boolean bUpCase) {
        try {
            StringBuilder buffer = new StringBuilder();
            // 把中文转化成byte数组
            byte[] b = strChinese.getBytes("GBK");
            for (int i = 0; i < b.length; i++) {
                if ((b[i] & 255) > 128) {
                    int char1 = b[i++] & 255;
                    // 左移运算符用“<<”表示，是将运算符左边的对象，向左移动运算符右边指定的位数，并且在低位补零。其实，向左移n位，就相当于乘上2的n次方
                    char1 <<= 8;
                    int chart = char1 + (b[i] & 255);
                    buffer.append(getPYIndexChar((char) chart, bUpCase));
                    continue;
                }
                char c = (char) b[i];
                if (!Character.isJavaIdentifierPart(c)) {
                    // 确定指定字符是否可以是 Java标识符中首字符以外的部分。
                    c = 'A';
                }
                buffer.append(c);
            }
            return buffer.toString();
        } catch (Exception e) {
            System.out.println((new StringBuilder()).append("\u53D6\u4E2D\u6587\u62FC\u97F3\u6709\u9519")
                    .append(e.getMessage()).toString());
        }
        return null;
    }

    /**
     * 得到首字母
     */
    private static char getPYIndexChar(char strChinese, boolean bUpCase) {

        int charGBK = strChinese;
        char result;
        if (charGBK >= 45217 && charGBK <= 45252) {
            result = 'A';
        } else if (charGBK >= 45253 && charGBK <= 45760) {
            result = 'B';
        } else if (charGBK >= 45761 && charGBK <= 46317) {
            result = 'C';
        } else if (charGBK >= 46318 && charGBK <= 46825) {
            result = 'D';
        } else if (charGBK >= 46826 && charGBK <= 47009) {
            result = 'E';
        } else if (charGBK >= 47010 && charGBK <= 47296) {
            result = 'F';
        } else if (charGBK >= 47297 && charGBK <= 47613) {
            result = 'G';
        } else if (charGBK >= 47614 && charGBK <= 48118) {
            result = 'H';
        } else if (charGBK >= 48119 && charGBK <= 49061) {
            result = 'J';
        } else if (charGBK >= 49062 && charGBK <= 49323) {
            result = 'K';
        } else if (charGBK >= 49324 && charGBK <= 49895) {
            result = 'L';
        } else if (charGBK >= 49896 && charGBK <= 50370) {
            result = 'M';
        } else if (charGBK >= 50371 && charGBK <= 50613) {
            result = 'N';
        } else if (charGBK >= 50614 && charGBK <= 50621) {
            result = 'O';
        } else if (charGBK >= 50622 && charGBK <= 50905) {
            result = 'P';
        } else if (charGBK >= 50906 && charGBK <= 51386) {
            result = 'Q';
        } else if (charGBK >= 51387 && charGBK <= 51445) {
            result = 'R';
        } else if (charGBK >= 51446 && charGBK <= 52217) {
            result = 'S';
        } else if (charGBK >= 52218 && charGBK <= 52697) {
            result = 'T';
        } else if (charGBK >= 52698 && charGBK <= 52979) {
            result = 'W';
        } else if (charGBK >= 52980 && charGBK <= 53688) {
            result = 'X';
        } else if (charGBK >= 53689 && charGBK <= 54480) {
            result = 'Y';
        } else if (charGBK >= 54481 && charGBK <= 55289) {
            result = 'Z';
        } else {
            result = (char) (65 + (new Random()).nextInt(25));
        }

        if (!bUpCase) {
            result = Character.toLowerCase(result);
        }

        return result;

    }
}
