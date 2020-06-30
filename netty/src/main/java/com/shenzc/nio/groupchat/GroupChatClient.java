package com.shenzc.nio.groupchat;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.awt.image.ImagingOpException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class GroupChatClient {
    //定义相关属性
    private final String HOST = "127.0.0.1";
    private final int PORT = 6667;
    private Selector selector;
    private SocketChannel socketChannel;
    private String username;

    //初始化
    public GroupChatClient(){
        try {
            selector = Selector.open();
            //链接服务器
            socketChannel = socketChannel.open(new InetSocketAddress(HOST, PORT));
            //设置非阻塞
            socketChannel.configureBlocking(false);
            //将socketChannel注册到selector

            socketChannel.register(selector, SelectionKey.OP_READ);
            //得到username
            username = socketChannel.getLocalAddress().toString().substring(1);
            System.out.println(username+"is OK");
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //向服务器发送消息
    public void sentToServer(String msg){
        try {
            msg = username+"说"+msg;
            socketChannel.write(ByteBuffer.wrap(msg.getBytes()));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //读取重服务器回复的消息
    public void reviceFromServer(){
        try {
            if (selector.select() > 0){
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()){
                    SelectionKey key = iterator.next();
                    if (key.isReadable()){
                        //得到相关的通道
                        SocketChannel socketChannel = (SocketChannel)key.channel();
                        //设置非阻塞
                        //socketChannel.configureBlocking(false);
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                        socketChannel.read(byteBuffer);
                        //将缓存区数据转成字符串
                        String msg = new String(byteBuffer.array());
                        System.out.println(msg.trim());
                    }
                }
                iterator.remove();
            }else{
                //System.out.println("没有可用的通道");
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException{
        //启动客户端
        final GroupChatClient groupChatClient = new GroupChatClient();
        //启动线程
        new Thread() {
            @Override
            public void run() {
                while (true){
                    groupChatClient.reviceFromServer();
                    try {
                        Thread.currentThread().sleep(3000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
        //发送数据到服务端
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String s = scanner.nextLine();
            groupChatClient.sentToServer(s);
        }
    }
}
