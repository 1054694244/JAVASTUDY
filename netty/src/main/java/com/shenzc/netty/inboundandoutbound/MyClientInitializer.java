package com.shenzc.netty.inboundandoutbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author shenzc
 * @create 2020-07-04-9:55
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MyLongToByteEnCoder());
        pipeline.addLast(new MyByteToLongDecoder());
        pipeline.addLast(new MyClientHandler());
    }
}
