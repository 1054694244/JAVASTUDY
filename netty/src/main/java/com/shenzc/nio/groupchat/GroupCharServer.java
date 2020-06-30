package com.shenzc.nio.groupchat;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

public class GroupCharServer {
    //定义属性
    private Selector selector;
    private ServerSocketChannel serverSocketChannel;
    private static final int port = 6667;

    //构造器
    //初始化工作
    public GroupCharServer(){
        try {
            //得到选择器
            selector = Selector.open();
            //初始化ServerSocketChannel
            serverSocketChannel = ServerSocketChannel.open();
            //绑定端口
            serverSocketChannel.socket().bind(new InetSocketAddress(port));
            //设置非阻塞模式
            serverSocketChannel.configureBlocking(false);
            //将sersversocketChannel注册到selector
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    //监听
    public void listen(){
        try {
            //循环处理
            while (true){
                if (selector.select() > 0){
                    //遍历得到的selectKeys集合
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> keys = selectionKeys.iterator();
                    while (keys.hasNext()){
                        SelectionKey key = keys.next();
                        //监听到accept
                        if (key.isAcceptable()){
                            SocketChannel socketChannel = serverSocketChannel.accept();
                            //设置非阻塞
                            socketChannel.configureBlocking(false);
                            //将socketChannel注册到selector,并且关联一个buffer
                            socketChannel.register(selector,SelectionKey.OP_READ);
                            //提示
                            System.out.println(socketChannel.getRemoteAddress()+"上线");
                        }
                        //监听到可读
                        if (key.isReadable()){
                            //处理读(专门写方法)
                            read(key);
                        }
                        //手动从集合中移出当前的selectorKey，防止重复操作
                        keys.remove();
                    }
                }else {

                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {

        }
    }

    //读取客户端消息，实现转发（除开自己）
    public void read(SelectionKey key){
        //定义一个SocketChannel
        SocketChannel socketChannel = null;
        try{
            //得到channel
            socketChannel = (SocketChannel)key.channel();
            //得到关联的buffer
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            if (socketChannel.read(byteBuffer) > 0){
                //吧缓冲区的数据转换为i字符串
                String msg = new String(byteBuffer.array());
                System.out.println("from客户端"+msg);
                //向其他的客户端转发消息,（专门写一个方法）
                sendToOtherClients(msg,socketChannel);
            }
        }catch (IOException e){
            try {
                //如果离线了
                System.out.println(socketChannel.getRemoteAddress()+"离线了");
                //取消注册,将注册到selector中的key取消
                key.cancel();
                //关闭流
                socketChannel.close();
            }catch (IOException e1){
                e1.printStackTrace();
            }
        }
    }

    //转发消息给其他客户端
    public void sendToOtherClients(String msg,SocketChannel self)throws IOException{
        System.out.println("服务器转发消息中");
        //排除自己
        /*keys.stream().filter(key -> key.channel() instanceof SocketChannel && key.channel() != self)
                .forEach(key -> {
                    //将msg存入buffer
                    ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    try {
                        socketChannel.write(buffer);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });*/
        for(SelectionKey key: selector.keys()) {

            //通过 key  取出对应的 SocketChannel
            Channel targetChannel = key.channel();

            //排除自己
            if(targetChannel instanceof  SocketChannel && targetChannel != self) {

                //转型
                SocketChannel socketChannel = (SocketChannel)targetChannel;
                //将msg 存储到buffer
                ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes());
                //将buffer 的数据写入 通道
                socketChannel.write(buffer);
            }
        }
    }

    //单 react 单线程
    public static void main(String[] args) {
        GroupCharServer groupCharServer = new GroupCharServer();
        groupCharServer.listen();
    }
}
