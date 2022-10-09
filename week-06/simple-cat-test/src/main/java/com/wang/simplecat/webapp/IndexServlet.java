package com.wang.simplecat.webapp;

import com.wang.sevlet.SimpleRequest;
import com.wang.sevlet.SimpleResponse;
import com.wang.sevlet.SimpleServlet;

/**
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public class IndexServlet implements SimpleServlet {
    
    @Override
    public void doGet(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception {
        String filePath =  simpleRequest.getPath();
        
        simpleResponse.writeStatic(filePath);
    }
    
    @Override
    public void doPost(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception {
        doGet(simpleRequest, simpleResponse);
    }
}
