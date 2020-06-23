package com.shenzc.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author shenzc
 * @create 2020-06-22-16:03
 */
public class BasicSelectorClient {
    //客户端
    public static void main(String[] args) throws IOException {
        //得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        //设置非阻塞模式
        socketChannel.configureBlocking(false);
        //提供服务器端的ip端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1",6666);
        //连接服务器
        if (!socketChannel.connect(inetSocketAddress)){
            while (!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞，可以做其他工作");
            }
        }

        //如果连接成功
        String str = "你傻吗？？？？";
        //根据字节数组大小来创建buffer，不需要指定大小
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());
        //写入数据
        socketChannel.write(byteBuffer);
        System.in.read();
    }
}
