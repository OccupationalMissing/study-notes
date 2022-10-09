package com.wang.simplecat.webapp;

import com.wang.sevlet.SimpleRequest;
import com.wang.sevlet.SimpleResponse;
import com.wang.sevlet.SimpleServlet;

import java.net.URI;

/**
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public class IndexServlet implements SimpleServlet {
    
    @Override
    public void doGet(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception {
        String uri = simpleRequest.getUri();
        String path = simpleRequest.getPath();
        String method = simpleRequest.getMethod();
        System.out.println("uri:" + uri);
        System.out.println("path:" + path);
        System.out.println("method:" + method);
        // 同理其他静态资源也可处理
        if(uri.contains("gif")){
            simpleResponse.writeStatic("static/cat.gif");
        }else{
            simpleResponse.writeStatic("static/index.html");
        }
    }
    
    @Override
    public void doPost(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception {
        doGet(simpleRequest, simpleResponse);
    }
}
