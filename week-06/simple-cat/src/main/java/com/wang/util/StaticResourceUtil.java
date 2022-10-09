package com.wang.util;

/**
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public class StaticResourceUtil {
    /**
     * 获取静态资源方法的绝对路径
     */
    public static String getAbsolutePath(String path) {
        String absolutePath = StaticResourceUtil.class.getResource("/").getPath();
        return absolutePath.replaceAll("\\\\", "/") + path;
    }
}
