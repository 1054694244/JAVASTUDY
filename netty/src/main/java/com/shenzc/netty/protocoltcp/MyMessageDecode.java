package com.shenzc.netty.protocoltcp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * @author shenzc
 * @create 2020-07-04-18:27
 */
public class MyMessageDecode extends ReplayingDecoder<MessageProtocol> {

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        System.out.println("MyMessageDecode被调用");
        //将得到的二进制字节码转成messageProtocol（数据包）对象
        int len = byteBuf.readInt();
        byte[] content = new byte[len];
        byteBuf.readBytes(content);
        //封装成MessageProtocol对象放入list集合中，传递给下一个handler处理
        list.add(new MessageProtocol(len,content));
    }
}
