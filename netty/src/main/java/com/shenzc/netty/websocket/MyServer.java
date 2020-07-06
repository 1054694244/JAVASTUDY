package com.shenzc.netty.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class MyServer {
    public static void main(String[] args) throws Exception{
        //创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //因为基于http协议，使用http的编解码器
                            pipeline.addLast(new HttpServerCodec());
                            //是以块方式处理的，添加chunkedWrite处理器
                            pipeline.addLast(new ChunkedWriteHandler());
                            /**
                             *说明：
                             * 1：http数据在传输过程中是分段的，HttpObjectAggregator就是可以将多个段聚合起来
                             * 2：这就是为什么当浏览器发送大量数据时，就会发出多次http请求的原因
                             */
                            pipeline.addLast(new HttpObjectAggregator(8192));
                            /**
                             * 说明：
                             * 1：对于webSocket，他的数据是以帧（frame）形式传递的
                             * 2：可以看到webSocketFrame，下面有6个子类
                             * 3：浏览器请求时：ws://localhost:7000/hello ,表示请求的uri
                             * 4：WebSocketServerProtocolHandler的核心功能是将http协议升级成ws协议，保持长连接
                             */
                            pipeline.addLast(new WebSocketServerProtocolHandler("/hello"));

                            //自定义的handler处理业务逻辑
                            pipeline.addLast(new MyTextWebSocketFrameHandler());

                        }
                    });
            System.out.println("服务器启动了");
            ChannelFuture channelFuture = serverBootstrap.bind(6667).sync();
            //监听关闭
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
