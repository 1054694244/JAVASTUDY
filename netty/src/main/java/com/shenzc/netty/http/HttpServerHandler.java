package com.shenzc.netty.http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.net.URI;

/**
 * 1:SimpleChannelInboundHandler是ChannelInboundHandlerAdapter的子类
 * 2:HttpObject 客户端和服务端相互通讯的数据被封装成HttpObject
 */
public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    /**
     * channelRead0：读取客户端数据
     * @param ctx
     * @param httpObject
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject httpObject) throws Exception {
        //判断msg是不是一个httpRequest请求
        if (httpObject instanceof HttpRequest){

            System.out.println("pipeLine"+ctx.pipeline().hashCode()+"HttpServerHandler"+this.hashCode());

            System.out.println("msg类型"+httpObject.getClass());
            System.out.println("客户端地址"+ctx.channel().remoteAddress());

            //获取到
            HttpRequest httpRequest = (HttpRequest)httpObject;
            //获取uri,过滤掉指定请求
            URI uri = new URI(httpRequest.uri());
            if ("/favicon.ico".equals(uri.getPath())){
                System.out.println("请求了图标，不做响应");
                return;
            }

            //回复信息给浏览器【Http协议】
            ByteBuf byteBuf = Unpooled.copiedBuffer("Hello,我是服务器", CharsetUtil.UTF_8);
            //构造一个http的响应，即httpResponse
            FullHttpResponse fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, byteBuf);
            fullHttpResponse.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain")
                                    .set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
            //将构建好的response放回
            ctx.writeAndFlush(fullHttpResponse);
        }
    }
}
