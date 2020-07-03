package com.shenzc.netty.codec2;

import com.shenzc.netty.codec.StudentPOJO;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

import java.util.Random;

public class NettyClientHandler extends ChannelInboundHandlerAdapter {

    /**
     * 当通道就绪时就会促发改方法
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //随机的发送一个Student或者Worker对象
        int random = new Random().nextInt(3);
        MyDataInfo.MyMessage myMessage = null;
        if (0 == random){
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DateType.StudentType).setStudent(MyDataInfo.Student.newBuilder().setId(5).setName("王五").build()).build();
        }else {
            myMessage = MyDataInfo.MyMessage.newBuilder().setDataType(MyDataInfo.MyMessage.DateType.WorkerType).setWorker(MyDataInfo.Worker.newBuilder().setAge(20).setName("王五").build()).build();
        }
        ctx.writeAndFlush(myMessage);
    }

    /**
     * 当通道有读取事件时，会促发
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf)msg;
        System.out.println("服务器回复的消息"+byteBuf.toString(CharsetUtil.UTF_8));
        System.out.println("服务器的地址"+ctx.channel().remoteAddress());
    }

    /**
     * 发生异常
     * @param ctx
     * @param cause
     * @throws Exception
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
