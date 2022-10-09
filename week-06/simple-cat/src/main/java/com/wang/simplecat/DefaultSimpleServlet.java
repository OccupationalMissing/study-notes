package com.wang.simplecat;

import com.wang.sevlet.SimpleRequest;
import com.wang.sevlet.SimpleResponse;
import com.wang.sevlet.SimpleServlet;

/**
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public class DefaultSimpleServlet implements SimpleServlet {
    @Override
    public void doGet(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception {
        String uri = simpleRequest.getUri();
        simpleResponse.write("404 - no this servlet : " + (uri.contains("?") ? uri.substring(0, uri.lastIndexOf("?")) : uri));
    }
    
    @Override
    public void doPost(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception {
        doGet(simpleRequest, simpleResponse);
    }
}
