package com.wang.simplecat.webapp;

import com.wang.sevlet.SimpleRequest;
import com.wang.sevlet.SimpleResponse;
import com.wang.sevlet.SimpleServlet;

import java.util.List;
import java.util.Map;

/**
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public class MyServlet2 implements SimpleServlet {
    
    @Override
    public void doGet(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception {
        String uri = simpleRequest.getUri();
        String path = simpleRequest.getPath();
        String method = simpleRequest.getMethod();
        Map<String, List<String>> parameters = simpleRequest.getParameters();
        StringBuilder parmStr =new StringBuilder();
        for (String parm : parameters.keySet()) {
            parmStr.append(parm + "=" + parameters.get(parm) + " ");
        }
    
        String content = "欢迎访问自定义Servlet2!\r\n" +
                "uri = " + uri + "\n" +
                "path = " + path + "\n" +
                "method = " + method + "\n" +
                "params = " + parmStr.toString();
        simpleResponse.write(content);
    }
    
    @Override
    public void doPost(SimpleRequest simpleRequest, SimpleResponse simpleResponse) throws Exception {
        doGet(simpleRequest, simpleResponse);
    }
}
