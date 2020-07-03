package com.shenzc.netty.codec2;

import com.shenzc.netty.codec.StudentPOJO;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;

public class NettyServer {
    public static void main(String[] args) throws Exception {

            //创建BossGroup和workGroup
            //创建两个线程组
            //bossGroup只处理连接请求，真正的与客户端业务处理都交给workGroup处理
            //两个都是无线循环
            //bossGroup和workGroup含有的子线程（NioEventLoop的个数是怎么确定？默认是cpu的核数）
            EventLoopGroup workGroup = new NioEventLoopGroup(10);
            EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        try {
            //创建服务器端启动的对象，可以配置启动参数
            ServerBootstrap bootstrap = new ServerBootstrap();

            //使用链式编程来进行设置
            bootstrap.group(bossGroup,workGroup)                            //设置两个线程组
                    .channel(NioServerSocketChannel.class)                  //使用NioServerSocketChannel，作为服务器的通道实现
                    .option(ChannelOption.SO_BACKLOG,128)                 //设置线程队列得到连接个数
                    .childOption(ChannelOption.SO_KEEPALIVE,true)       //设置保持活动连接状态
                    //改handler对应的bossGroup生效，childHandler实在workGroup生效
                    .childHandler(new ChannelInitializer<SocketChannel>() {    //给我的workerGroup的EventLoop对应的管道设置处理器
                        //创建一个通道初始化对象，给pipeline设置处理器
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            System.out.println("客户sockeChannel hashcode"+socketChannel.hashCode());
                            //加入编码器，protoBuf decoder,指定对哪种对象进行编码
                            socketChannel.pipeline().addLast("decoder",new ProtobufDecoder(MyDataInfo.MyMessage.getDefaultInstance()));
                            //可以使用一个集合管理socketChannel，在推送消息时可以将业务放入到各个channel对应的NIOEventLoop的taskQueue或者schedule0TaskQueue
                            socketChannel.pipeline().addLast(new NettyServerHandler());
                        }
                    });
            System.out.println("服务器IS READY");

            //绑定端口,并且同步，生成一个ChannelFuture对象
            //启动服务器
            ChannelFuture cf = bootstrap.bind(6667).sync();

            //给channelFuture注册监听器，监控我们关心的事件
            cf.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture channelFuture) throws Exception {
                    if (channelFuture.isSuccess()){
                        System.out.println("监控端口 6667 成功");
                    }else {
                        System.out.println("监听失败");
                    }
                }
            });

            //对关闭通道进行监听
            cf.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }
}
