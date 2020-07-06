package com.shenzc.netty.inboundandoutbound;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * @author shenzc
 * @create 2020-07-04-9:57
 */
public class MyLongToByteEnCoder extends MessageToByteEncoder<Long> {
    /**
     * 编码
     * @param ctx
     * @param aLong
     * @param byteBuf
     * @throws Exception
     */
    @Override
    protected void encode(ChannelHandlerContext ctx, Long aLong, ByteBuf byteBuf) throws Exception {
        System.out.println("MyLongToByteEnCoder中的encoder方法被调用");
        System.out.println("msg"+aLong);
        byteBuf.writeLong(aLong);
    }
}
