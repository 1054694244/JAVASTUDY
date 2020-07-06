package com.shenzc.netty.inboundandoutbound;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author shenzc
 * @create 2020-07-04-9:52
 */
public class MyClient {
    public static void main(String[] args) throws Exception{
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new MyClientInitializer());
            ChannelFuture ch = bootstrap.connect("127.0.0.1", 6667).sync();
            ch.channel().closeFuture().sync();
        }finally {
            group.shutdownGracefully();
        }
    }
}
