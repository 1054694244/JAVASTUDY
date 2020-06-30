package com.shenzc.nio.copy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

//服务器端
public class NewIOServer {
    public static void main(String[] args) throws IOException {
        InetSocketAddress inetSocketAddress = new InetSocketAddress(7001);
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        ServerSocket serverSocket = serverSocketChannel.socket();
        serverSocket.bind(inetSocketAddress);

        ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            while (socketChannel.read(byteBuffer) != -1){
                //倒带
                byteBuffer.rewind();
            }
        }
    }
}
