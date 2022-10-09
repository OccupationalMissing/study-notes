package com.wang.simplecat;

import com.wang.sevlet.SimpleResponse;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.util.internal.StringUtil;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;

/**
 * @author OccupationalMissing
 * @date 2022/10/7
 */
public class HttpSimpleResponse implements SimpleResponse {
    
    private HttpRequest request;
    private ChannelHandlerContext context;
    
    public HttpSimpleResponse(HttpRequest request, ChannelHandlerContext context) {
        this.request = request;
        this.context = context;
    }
    
    @Override
    public void write(String content) throws Exception {
        // 处理content为空的情况
        if (StringUtil.isNullOrEmpty(content)) {
            return;
        }
        
        // 创建响应对象
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                // 根据响应体内容大小为response对象分配存储空间
                Unpooled.wrappedBuffer(content.getBytes("UTF-8")));
        
        // 获取响应头
        HttpHeaders headers = response.headers();
        // 设置响应体类型
        headers.set(HttpHeaderNames.CONTENT_TYPE, "text/json");
        // 设置响应体长度
        headers.set(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        // 设置缓存过期时间
        headers.set(HttpHeaderNames.EXPIRES, 0);
        // 若HTTP请求是长连接，则响应也使用长连接
        if (HttpUtil.isKeepAlive(request)) {
            headers.set(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        }
        // 将响应写入到Channel
        context.writeAndFlush(response);
    }
    
    /**
     * 静态资源响应写入Channel
     *
     * @param filePath
     * @throws Exception
     */
    @Override
    public void writeStatic(String filePath) throws Exception {
        URI resource = this.getClass().getResource(filePath).toURI();
        File file = new File(resource);
        FileInputStream stream = new FileInputStream(file);
        byte[] b = new byte[1024];
        int c = 0;
        while ((c = stream.read(b)) != -1) {
            ByteBuf buffer = Unpooled.copiedBuffer(b);
            FullHttpResponse response = new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1, HttpResponseStatus.OK, buffer
            );
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html;charset=utf-8");
            response.headers().set(HttpHeaderNames.ACCEPT_RANGES, "bytes");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, buffer.readableBytes());
            this.context.writeAndFlush(response);
        }
    }
}
