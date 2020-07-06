package com.shenzc.netty.inboundandoutbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author shenzc
 * @create 2020-07-04-9:46
 */
public class MyByteToLongDecoder2 extends ReplayingDecoder<Void> {

    /**
     *不需要判断是否有可用字节可读，父类会帮我们判断
     * @param ctx
     * @param byteBuf
     * @param list
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyByteToLongDecoder进行decode");
        list.add(byteBuf.readLong());
    }

}
