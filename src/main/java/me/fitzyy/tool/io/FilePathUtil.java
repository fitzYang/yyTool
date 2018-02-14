/*
 * Copyright (c) 2018, Fitzyy. All Rights Reserved.
 */

package me.fitzyy.tool.io;

import com.google.common.io.Files;

import org.apache.commons.lang3.StringUtils;

import me.fitzyy.tool.base.Platforms;
import me.fitzyy.tool.lang.YyStringUtil;

/**
 * <p>关于文件路径的工具集 </p>
 *
 * @author sog
 * @version 1.0
 * @since JDK 1.7
 */
public class FilePathUtil {


    /**
     * 在Windows环境里，兼容Windows上的路径分割符，将 '/' 转回 '\'
     *
     * @param path 路径
     * @return 转换后的路径
     */
    public static String normalizePath(String path) {
        if (Platforms.FILE_PATH_SEPARATOR_CHAR == Platforms.WINDOWS_FILE_PATH_SEPARATOR_CHAR
                && StringUtils.indexOf(path, Platforms.LINUX_FILE_PATH_SEPARATOR_CHAR) != -1) {
            return StringUtils.replaceChars(path, Platforms.LINUX_FILE_PATH_SEPARATOR_CHAR,
                    Platforms.WINDOWS_FILE_PATH_SEPARATOR_CHAR);
        }
        return path;

    }

    /**
     * 将路径整理，如 "a/../b"，整理成 "b"
     *
     * @param path 路径
     * @return 调整路径
     */
    public static String simplifyPath(String path) {
        return Files.simplifyPath(path);
    }

    /**
     * 以拼接路径名
     *
     * @param baseName   基本路径
     * @param appendName 要添加的路径
     * @return 拼接后的路径
     */
    public static String contact(String baseName, String... appendName) {
        if (appendName.length == 0) {
            return baseName;
        }

        StringBuilder contactName = new StringBuilder(baseName);
        if (YyStringUtil.endWith(baseName, Platforms.FILE_PATH_SEPARATOR_CHAR)) {
            contactName.append(appendName[0]);
        } else {
            contactName.append(Platforms.FILE_PATH_SEPARATOR_CHAR);
            contactName.append(appendName[0]);
        }

        if (appendName.length > 1) {
            for (int i = 1; i < appendName.length; i++) {
                contactName
                        .append(Platforms.FILE_PATH_SEPARATOR_CHAR)
                        .append(appendName[i]);
            }
        }
        contactName.append(Platforms.FILE_PATH_SEPARATOR_CHAR);

        return contactName.toString();
    }

    /**
     * 获得上层目录的路径
     *
     * @param path 上层路径
     * @return 目录路径
     */
    public static String getParentPath(String path) {
        String parentPath = path;

        if (Platforms.FILE_PATH_SEPARATOR.equals(parentPath)) {
            return parentPath;
        }

        parentPath = YyStringUtil.removeEnd(parentPath, Platforms.FILE_PATH_SEPARATOR_CHAR);

        int idx = parentPath.lastIndexOf(Platforms.FILE_PATH_SEPARATOR_CHAR);
        if (idx >= 0) {
            parentPath = parentPath.substring(0, idx + 1);
        } else {
            parentPath = Platforms.FILE_PATH_SEPARATOR;
        }

        return parentPath;
    }

    /**
     * 获得参数clazz所在的Jar文件的绝对路径
     */
    public static String getJarPath(Class<?> clazz) {
        return clazz.getProtectionDomain().getCodeSource().getLocation().getFile();
    }
}
