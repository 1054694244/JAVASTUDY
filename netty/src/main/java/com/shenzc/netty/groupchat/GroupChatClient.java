package com.shenzc.netty.groupchat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.util.Scanner;

public class GroupChatClient {

    private final String host;
    private final int port;

    public GroupChatClient(String host,int port){
        this.host = host;
        this.port = port;
    }

    public void run()throws Exception{
        EventLoopGroup eventExecutors = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventExecutors)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            //向pipeline添加一个解码器
                            pipeline.addLast("decoder",new StringDecoder());
                            //向pipeline添加一个编码器
                            pipeline.addLast("encoder",new StringEncoder());
                            //向pipeLine添加自己的处理器
                            pipeline.addLast(new GroupChatClientHandler());
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(host, port).sync();
            Channel channel = channelFuture.channel();
            System.out.println("-------"+channel.localAddress()+"---------");
            //客户端需要输入信息，创建一个扫描器
            Scanner scanner =new Scanner(System.in);
            while (scanner.hasNext()){
                String msg = scanner.nextLine();
                //通过channel发送msg到服务器端
                channel.writeAndFlush(msg);
            }
            channelFuture.channel().closeFuture();
        }finally {
            eventExecutors.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception{
        new GroupChatClient("localhost",6667).run();
    }
}
