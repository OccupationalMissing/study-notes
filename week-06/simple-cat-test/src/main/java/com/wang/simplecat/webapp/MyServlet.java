package com.wang.simplecat.webapp;

import com.wang.sevlet.SimpleRequest;
import com.wang.sevlet.SimpleResponse;
import com.wang.sevlet.SimpleServlet;

/**
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public class MyServlet implements SimpleServlet {
    @Override
    public void doGet(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception {
        String uri = simpleRequest.getUri();
        String path = simpleRequest.getPath();
        String method = simpleRequest.getMethod();
        String name = simpleRequest.getParameter("name");
        
        String content = "欢迎访问自定义Servlet!\r\n" +
                "uri = " + uri + "\n" +
                "path = " + path + "\n" +
                "method = " + method + "\n" +
                "param = " + name;
        simpleResponse.write(content);
    }
    
    @Override
    public void doPost(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception {
        doGet(simpleRequest, simpleResponse);
    }
}