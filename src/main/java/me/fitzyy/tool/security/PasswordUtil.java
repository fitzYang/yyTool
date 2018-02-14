/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.security;

import com.google.common.base.Throwables;

import org.apache.commons.lang3.StringUtils;

import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;

import me.fitzyy.tool.lang.EncodeUtil;


/**
 * <p> 密码工具 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
@SuppressWarnings("unused")
public class PasswordUtil {

    /**
     * sha-算法名称
     */
    public static final  String       HASH_ALGORITHM   = "SHA-1";
    /**
     * 计算次数
     */
    public static final  int          HASH_INTERATIONS = 1024;
    /**
     * 长度
     */
    private static final int          SALT_SIZE        = 8;
    private static       SecureRandom random           = new SecureRandom();


    /**
     * 将字节的盐值转换为字符串
     *
     * @param salts 盐值信息
     * @return 字符串盐值
     */
    public static String salt(byte[] salts) {
        final String strSalt = EncodeUtil.encodeHex(salts);
        return StringUtils.lowerCase(strSalt);
    }

    /**
     * 比较密码是否正确
     *
     * @param salt          密码盐值
     * @param password      数据库加密密码.
     * @param plainPassword 明文密码.
     * @return true, in user.
     */
    public static boolean checkPassword(String salt, String password, String plainPassword) {
        byte[] saltHex = EncodeUtil.decodeHex(salt);
        byte[] hashPassword = sha1(plainPassword.getBytes(), saltHex);
        final String hexPassword = EncodeUtil.encodeHex(hashPassword);
        return StringUtils.equals(StringUtils.lowerCase(hexPassword), password);
    }


    /**
     * 生成密码
     *
     * @param salt     盐值
     * @param password 明文密码
     * @return 混淆后的密码
     */
    public static String password(byte[] salt, String password) {
        final byte[] passwordBytes = PasswordUtil.sha1(password.getBytes(), salt);
        final String saltPassword = EncodeUtil.encodeHex(passwordBytes);
        return StringUtils.lowerCase(saltPassword);
    }


    public static byte[] sha1(byte[] input, byte[] salt) {
        return digest(input, salt, HASH_INTERATIONS);
    }

    /**
     * 生成随机的Byte[]作为salt.
     *
     * 默认获取8个字节的盐值
     */
    public static byte[] generateSalt() {
        return generateSalt(SALT_SIZE);
    }

    /**
     * 生成随机的Byte[]作为salt.
     *
     * @param size 长度
     * @return 盐值信息
     */
    public static byte[] generateSalt(int size) {
        byte[] bytes = new byte[size];
        random.nextBytes(bytes);
        return bytes;
    }

    /**
     * 对字符串进行散列, 支持md5与sha1算法.
     */
    private static byte[] digest(byte[] input, byte[] salt, int iterations) {
        try {
            MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
            if (salt != null) {
                digest.update(salt);
            }
            byte[] result = digest.digest(input);
            for (int i = 1; i < iterations; i++) {
                digest.reset();
                result = digest.digest(result);
            }
            return result;
        } catch (GeneralSecurityException e) {
            Throwables.throwIfUnchecked(e);
            throw new AssertionError(e);
        }
    }
}
