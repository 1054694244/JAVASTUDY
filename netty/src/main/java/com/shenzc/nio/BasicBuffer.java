package com.shenzc.nio;

import java.nio.IntBuffer;

/**
 * @author shenzc
 * @create 2020-06-19-10:37
 */
public class BasicBuffer {
    public static void main(String[] args) {

        //创建buffer
        //private int position = 0 ：数组下标初始位置;
        //private int limit：可以读写最大限制;
        // int capacity：数组容量;
        IntBuffer intBuffer = IntBuffer.allocate(5);

        /**
         *put什么类型，get就用什么类型
         */
        for (int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i*2);
        }

        //每次都要进行读写转换
        /**
         * limit = position; 表示存了几个元素，就只能读写到几个元素
         * position = 0;     重置数组下标为0
         * mark = -1;
         * return this;
         */
        intBuffer.flip();

        /**
         * 设置只可读
         */
        IntBuffer intBuffer1 = intBuffer.asReadOnlyBuffer();

        //和迭代器很像
        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }

    }
}
