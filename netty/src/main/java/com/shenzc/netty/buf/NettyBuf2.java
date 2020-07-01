package com.shenzc.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

public class NettyBuf2 {
    public static void main(String[] args) {
        /**
         * 创建buffer，指定内容并且编码方式
         */
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello,world!123", CharsetUtil.UTF_8);
        //使用相关方法
        if (byteBuf.hasArray()){
            byte[] bytes = byteBuf.array();
            //将bytes转成字符串处理
            System.out.println(new String(bytes, Charset.forName("utf-8")));

            System.out.println("真实的byteBuffer"+byteBuf);

            System.out.println(byteBuf.arrayOffset());  //0

            System.out.println(byteBuf.readerIndex());  //0

            System.out.println(byteBuf.writerIndex());  //15

            //System.out.println(byteBuf.readByte());
            //getBytes不会导致readerIndex变化
            System.out.println((char) byteBuf.getByte(0));

            System.out.println(byteBuf.capacity());     //45

            int i = byteBuf.readableBytes();    //可读的字节数15
            System.out.println(i);

            //取出对应的字符
            System.out.println(byteBuf.getCharSequence(0,4,Charset.forName("utf-8")));

        }
    }
}
