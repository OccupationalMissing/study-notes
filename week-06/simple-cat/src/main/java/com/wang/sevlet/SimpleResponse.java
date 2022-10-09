package com.wang.sevlet;

/**
 * 参照HeroResponse制定response规范
 *
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public interface SimpleResponse {
    /**
     * 将响应写入到Channel
     *
     * @param content
     * @throws Exception
     */
    void write(String content) throws Exception;
    
    /**
     * 静态资源响应写入Channel
     *
     * @param filePath
     * @throws Exception
     */
    void writeStatic(String filePath) throws Exception;
}
