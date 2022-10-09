package com.wang.simplecat;

import com.wang.sevlet.SimpleRequest;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;

import java.util.List;
import java.util.Map;

/**
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public class HttpSimpleRequest implements SimpleRequest {
    private HttpRequest request;
    
    public HttpSimpleRequest(HttpRequest request) {
        this.request = request;
    }
    
    @Override
    public String getUri() {
        return request.uri();
    }
    
    @Override
    public String getPath() {
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.path();
    }
    
    @Override
    public String getMethod() {
        return request.method().name();
    }
    
    @Override
    public Map<String, List<String>> getParameters() {
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        return decoder.parameters();
    }
    
    @Override
    public List<String> getParameters(String name) {
        return getParameters().get(name);
    }
    
    @Override
    public String getParameter(String name) {
        List<String> parameters = getParameters(name);
        if (parameters == null || parameters.size() == 0) {
            return null;
        }
        return parameters.get(0);
    }
}
