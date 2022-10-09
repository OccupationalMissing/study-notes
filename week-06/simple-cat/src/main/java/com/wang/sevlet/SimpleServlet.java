package com.wang.sevlet;

/**
 * 参照HeroServlet制定servlet规范
 *
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public interface SimpleServlet {
    /**
     * 处理客户端发来的Get请求
     *
     * @param simpleRequest
     * @param simpleResponse
     */
    void doGet(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception;
    
    /**
     * 处理客户端发来的Post请求
     *
     * @param simpleRequest
     * @param simpleResponse
     */
    void doPost(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception;
}
