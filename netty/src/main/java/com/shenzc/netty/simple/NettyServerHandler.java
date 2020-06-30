package com.shenzc.netty.simple;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.util.CharsetUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 说明：
 * 1：我们自定义一个handler，需要继承netty规定好的某个handler适配器（规范）
 * 2：这时我们自定义一个handler，才能称为一个handler
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据实际（这里我们可以读取客户端发送的消息）

    /**
     *
     * @param ctx：ChannelHandlerContext：上下文对象，含有 管道：pipeLine    通道channel     地址
     * @param msg：Object：客户端发送的消息
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //比如我们这里有一个非常耗费时间的业务-》异步执行-》提交改channel对应的NIOEventLoop的taskQueue中，
        //Thread.sleep(10000);
        //ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵1", CharsetUtil.UTF_8));

        //解决方案1：用户程序自定义的普通任务,同一个线程，但是是两个任务，
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵1", CharsetUtil.UTF_8));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        ctx.channel().eventLoop().execute(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵3", CharsetUtil.UTF_8));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        //解决方案2：用户自定义定时任务，-》该任务是提交到scheduleTaskQueue，同一个线程
        ctx.channel().eventLoop().schedule(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(5000);
                    ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵4", CharsetUtil.UTF_8));
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        },5, TimeUnit.SECONDS);

        /*System.out.println("服务器读取线程"+Thread.currentThread().getName());
        System.out.println("server ctx" + ctx);
        System.out.println("看看Channel和pipeLine的关系");
        Channel channel = ctx.channel();
        ChannelPipeline pipeline = ctx.pipeline();  //pipeLine本质是一个双向链表，出栈入栈问题
        //将我们的msg转成一个byteBuf
        //ByteBuf是netty提供的，不是nio的byteBuffer，性能更高
        ByteBuf byteBuf = (ByteBuf)msg;
        //System.out.println("客户端发送消息是"+new String(byteBuf.array()));
        System.out.println("客户端发送消息是"+byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("客户端地址"+ctx.channel().remoteAddress());*/
    }

    /**
     * 数据读取完毕
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

        //write+flush:将数据写入到缓冲，并且刷新
        //一般来讲，我们对这个发送的数据进行编码。
        ctx.writeAndFlush(Unpooled.copiedBuffer("hello, 客户端~(>^ω^<)喵2", CharsetUtil.UTF_8));
    }

    /**
     * 处理异常：一般需要关闭通道
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
