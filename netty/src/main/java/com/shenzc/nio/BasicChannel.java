package com.shenzc.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.Channel;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

/**
 * @author shenzc
 * @create 2020-06-19-10:45
 */
public class BasicChannel {
    public static void main(String[] args) throws IOException {
        test5();
    }

    //读取
    public static void test2()throws IOException{
        File file = new File("D:\\java\\workspace\\idea\\JAVASTUDY\\netty\\a.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
        //将channel中的数据读取到buffer中
        channel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        fileInputStream.close();
    }

    //写入
    public static void test1()throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\java\\workspace\\idea\\JAVASTUDY\\netty\\a.txt");
        String str = "你是煞笔吗?";
        //创建channel,实际上是fileChannelImpl
        FileChannel fileChannel = fileOutputStream.getChannel();
        //创建bytebuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put(str.getBytes());
        //反转
        byteBuffer.flip();
        //写入到channel
        fileChannel.write(byteBuffer);
        fileOutputStream.close();
    }

    //拷贝
    public static void test3() throws IOException {
        File file = new File("D:\\java\\workspace\\idea\\JAVASTUDY\\netty\\a.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\java\\workspace\\idea\\JAVASTUDY\\netty\\b.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int)file.length());
        while (true){
            //每次读完之后，要将buffer复位，position = 0
            /**
             * position = 0;
             * limit = capacity;
             * mark = -1;
             */
            byteBuffer.clear();
            int read = channel.read(byteBuffer);
            if (read == -1){
                break;
            }
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
        }
        fileInputStream.close();
        fileOutputStream.close();
    }

    //拷贝
    public static void test4() throws IOException{
        File file = new File("D:\\java\\workspace\\idea\\JAVASTUDY\\netty\\a.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel channel = fileInputStream.getChannel();
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\java\\workspace\\idea\\JAVASTUDY\\netty\\c.txt");
        FileChannel fileChannel = fileOutputStream.getChannel();
        fileChannel.transferFrom(channel,0,channel.size());
        fileInputStream.close();
        fileOutputStream.close();
    }

    //在内存（堆外内存）中修改文件中的类容，不需要在操作系统中拷贝
    //TODO 有问题乱码
    public static void test5() throws IOException{
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\java\\workspace\\idea\\JAVASTUDY\\netty\\a.txt","rw");
        FileChannel channel = randomAccessFile.getChannel();

        /**
         * FileChannel.MapMode.READ_WRITE：读写方式
         * position：可以修改的起始位置
         * size：一共多少个字节映射到内存，不包括6，起始从0开始
         */
        MappedByteBuffer byteBuffer = channel.map(FileChannel.MapMode.READ_WRITE,0,6);
        byteBuffer.put(3,(byte)'H');
        randomAccessFile.close();
    }
}
