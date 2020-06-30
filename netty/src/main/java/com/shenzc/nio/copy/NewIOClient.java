package com.shenzc.nio.copy;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

//服务器端
public class NewIOClient {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",7001));
        String fileName = "D:\\java\\workspace\\JAVASTUDY\\netty\\a.txt";
        //得到一个文件的channel
        FileChannel fileChannel = new FileInputStream(fileName).getChannel();
        //准备发送
        long startTime = System.currentTimeMillis();
        //在linux下一个transferTo方法就可以完成传输
        //在windows下，一次调用transferTo只能发送8M文件。
        //传输时的位置,文件分段 》8M
        long count = fileChannel.transferTo(0, fileChannel.size(), socketChannel);
        System.out.println("发送总字节数： " + count + ", 耗时： " + (System.currentTimeMillis() - startTime));
        socketChannel.close();
        fileChannel.close();
    }
}
