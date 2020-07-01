package com.shenzc.netty.hearteat;

import com.shenzc.netty.groupchat.GroupChatServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class HeartServer {
    public static void main(String[] args) throws Exception{
        //创建两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    //.option(ChannelOption.SO_BACKLOG,128)
                    //.childOption(ChannelOption.SO_KEEPALIVE,true)
                    //在bossGroup增加一个日志处理器
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //加入一个netty提供的IdleStateHandler
                            /**
                             * IdleStateHandler是netty提供的处理空闲状态的处理器
                             * readerIdleTime：表示多长时间没有读，就会发送一个心脏检测包检测是否连接
                             * writerIdleTime：表示多长时间没有写，就会发送一个心脏检测包检测是否连接
                             * allIdleTime：表示多长时间没有读写，就会发送一个心脏检测包检测是否连接
                             */
                            //当IdleStateEvent促发后机会传给管道的下一个handler去处理，通过回调触发下一个handle的userEventTiggerd方法，在该方法处理IdleStateEvent
                            pipeline.addLast(new IdleStateHandler(3,5,7, TimeUnit.SECONDS));
                            //加入一个对空闲检测进一步处理的handler（自定义）
                            //向pipeLine添加自己的处理器
                            pipeline.addLast(new HeartServerHandler());
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
