package com.shenzc.netty.inboundandoutbound;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @author shenzc
 * @create 2020-07-04-9:46
 */
public class MyByteToLongDecoder extends ByteToMessageDecoder {

    /**
     * decode会根据接受的数据，被调用多次，知道确定没有新的元素添加到list集合中
     * 或者bytebug没有更多的可读字节为止
     * 如果list不为空，就会将list转交给下一个channelInboundHandler处理，该处理器方法也会被调用多次
     * @param ctx：上下文对象
     * @param byteBuf：入站的bytebuf
     * @param list：list集合，将解码后的数据传给下一个handler进行处理
     * @throws Exception
     */
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyByteToLongDecoder进行decode");
        //因为long是8个字节，
        if (byteBuf.readableBytes()>=8){
            list.add(byteBuf.readLong());
        }
    }

}
