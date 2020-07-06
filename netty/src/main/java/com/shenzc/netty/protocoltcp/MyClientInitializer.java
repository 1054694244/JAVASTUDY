package com.shenzc.netty.protocoltcp;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * @author shenzc
 * @create 2020-07-04-9:52
 */
public class MyClientInitializer extends ChannelInitializer<SocketChannel>{
    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(new MyMessageEncode());
        pipeline.addLast(new MyMessageDecode());
        pipeline.addLast(new MyClientHandler());
    }
}
