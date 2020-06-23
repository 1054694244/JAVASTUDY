package com.shenzc.nio;

/**
 * @author shenzc
 * @create 2020-06-22-11:07
 */

import com.sun.security.ntlm.Server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * scattering：将数组写入到buffer中，可以使用buffer数组，依次写入  【分散】
 *
 * gathering：从buffer读取数组是，可以使用buffer数组，依次读取 【聚集】
 */
public class BasicBuffer2 {
    public static void main(String[] args)throws IOException {
        test1();
    }

    //使用serverSocketChannel和socketChannel
    public static void test1()throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        InetSocketAddress inetSocketAddress = new InetSocketAddress(9898);
        //绑定端口到 socket，并且启动
        serverSocketChannel.socket().bind(inetSocketAddress);
        //创建buffer数组
        ByteBuffer[] byteBuffer = new ByteBuffer[2];

        byteBuffer[0] = ByteBuffer.allocate(5);
        byteBuffer[1] = ByteBuffer.allocate(3);
        //等待客户端连接 talnet
        SocketChannel socketChannel = serverSocketChannel.accept();
        //假设从客户端接受8个字节
        long messageLength = 8;
        while (true){
            long byteRead = 0;
            while (byteRead < messageLength){
                long read = socketChannel.read(byteBuffer);
                byteRead += read;
                System.out.println("read"+read);
            }
            System.out.println(byteRead);
            //反转
            Stream.of(byteBuffer).forEach(buffer -> buffer.flip());
            //将数据显示到客户端
            long byteWrite = 0;
            while (byteWrite < messageLength){
                long write = socketChannel.write(byteBuffer);
                byteWrite += write;
                System.out.println("write"+write);
            }
            System.out.println(byteWrite);
            Stream.of(byteBuffer).forEach(buffer -> buffer.clear());
        }
    }
}
