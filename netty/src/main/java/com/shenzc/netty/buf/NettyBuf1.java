package com.shenzc.netty.buf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

public class NettyBuf1 {
    public static void main(String[] args) {
        /**
         * 创建一个对象，该对象包含一个arr，是一个byte[10]
         * 在netty中buffer不需要flip，因为d底层维护了readerIndex（读取索引，初始为0）和writeIndex（写入位置，初始为0）
         * 通过readIndex和writeIndex和capacity将buffer分成了三个区域
         * 0-readerIndex：已经读取区
         * readerIndex-writeIndex：可以读取区
         * writeIndex-capacity：可以写入区
         */
        ByteBuf buffer = Unpooled.buffer(10);
        for (int i=0;i<buffer.capacity();i++){
            buffer.writeByte(i);
        }
        //输出 capacity是buffer的数组大小
        /*for (int i=0;i<buffer.capacity();i++){
            System.out.println(buffer.getByte(i));
        }*/
        for (int i=0;i<buffer.capacity();i++){
            System.out.println(buffer.readByte());
        }
    }
}
