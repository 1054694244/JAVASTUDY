package com.shenzc.nio;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author shenzc
 * @create 2020-06-22-14:49
 */
public class BasicSelectorServer {
    //服务器端
    public static void main(String[] args)throws IOException {
//创建serverSocketChannel  相当于bio的serverSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        //得到一个selector对象
        Selector selector = Selector.open();
        //绑定一个端口6666
        InetSocketAddress inetAddress = new InetSocketAddress(6666);
        serverSocketChannel.socket().bind(inetAddress);
        //设置为非阻塞
        serverSocketChannel.configureBlocking(false);
        //把serverSocketChannel注册到selector，关心事件OP_ACCPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        //循环等待客户端连接
        while (true){
            //这里我们等待了一秒，如果没有事件发生，就返回
            if (selector.select(1000)==0){
                System.out.println("等待了一秒没有事件发生");
                continue;
            }
            /**
             * 如果>0,则获取相关事件的selectorkeys
             * 如果>0,表示已经获取到关注的事件
             * 通过selectorkeys可以反向获取到channel
             */
            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                //如果是客户端第一次访问连接，就会调用此事件
                if (key.isAcceptable()){
                    //在客户端生成一个socketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    //产生了socketChannel，就要见它设置为非阻塞
                    socketChannel.configureBlocking(false);
                    //将socketChannel注册到selecotr,同时绑定一个buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }
                if (key.isReadable()){
                    //通过key，反向获取到对应的channel
                    SocketChannel channel = (SocketChannel)key.channel();
                    //获取到channel关联的buffer
                    ByteBuffer byteBuffer = (ByteBuffer)key.attachment();
                    //将channel中的数据读取到buffer中,这个buffer是靠近服务器端的buffer
                    channel.read(byteBuffer);
                    System.out.println("客户端"+new String(byteBuffer.array()));
                }
                //手动从集合中移出当前的selectorKey，防止重复操作
                iterator.remove();
            }
        }
    }
}
