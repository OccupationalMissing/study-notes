package com.wang.simplecat;

import com.wang.sevlet.SimpleRequest;
import com.wang.sevlet.SimpleResponse;
import com.wang.sevlet.SimpleServlet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.HttpRequest;

import java.util.Map;

/**
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public class SimpleCatHandler extends ChannelInboundHandlerAdapter {
    /**
     * 线程安全
     */
    private Map<String, SimpleServlet> nameToServletMap;
    
    /**
     * 线程不安全
     */
    private Map<String, String> nameToClassNameMap;
    
    public SimpleCatHandler(Map<String, SimpleServlet> nameToServletMap, Map<String, String> nameToClassNameMap) {
        this.nameToServletMap = nameToServletMap;
        this.nameToClassNameMap = nameToClassNameMap;
    }
    
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (msg instanceof HttpRequest) {
            HttpRequest request = (HttpRequest) msg;
            String uri = request.uri();
            String servletName = "";
            if (uri.contains("?") && uri.contains("/")) {
                servletName = uri.substring(uri.lastIndexOf("/") + 1, uri.indexOf("?"));
            } else if (uri.contains("indexservlet")) {
                servletName = "indexservlet";
            }
            
            SimpleServlet servlet = new DefaultSimpleServlet();
            //第一次访问，Servlet是不会被加载的
            //初始化加载的只是类全限定名称，懒加载
            //如果访问Servlet才会去初始化它对象
            if (nameToServletMap.containsKey(servletName)) {
                servlet = nameToServletMap.get(servletName);
            } else if (nameToClassNameMap.containsKey(servletName)) {
                // double-check，双重检测锁：为什么要在锁前判断一次，还要在锁后继续判断一次？
                if (nameToServletMap.get(servletName) == null) {
                    synchronized (this) {
                        if (nameToServletMap.get(servletName) == null) {
                            // 获取当前Servlet的全限定性类名
                            String className = nameToClassNameMap.get(servletName);
                            // 使用反射机制创建Servlet实例
                            servlet = (SimpleServlet) Class.forName(className).newInstance();
                            // 将Servlet实例写入到nameToServletMap
                            nameToServletMap.put(servletName, servlet);
                        }
                    }
                }
            } //  end-else if
            
            // 代码走到这里，servlet肯定不空
            SimpleRequest req = new HttpSimpleRequest(request);
            SimpleResponse res = new HttpSimpleResponse(request, ctx);
            // 根据不同的请求类型，调用servlet实例的不同方法
            if (request.method().name().equalsIgnoreCase("GET")) {
                servlet.doGet(req, res);
            } else if (request.method().name().equalsIgnoreCase("POST")) {
                servlet.doPost(req, res);
            }
            ctx.close();
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
