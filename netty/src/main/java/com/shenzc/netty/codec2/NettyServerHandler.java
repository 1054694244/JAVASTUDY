package com.shenzc.netty.codec2;

import com.shenzc.netty.codec.StudentPOJO;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * 说明：
 * 1：我们自定义一个handler，需要继承netty规定好的某个handler适配器（规范）
 * 2：这时我们自定义一个handler，才能称为一个handler
 */
public class NettyServerHandler extends SimpleChannelInboundHandler<MyDataInfo.MyMessage> {
//public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    //读取数据实际（这里我们可以读取客户端发送的消息）

    /**
     *
     * @param ctx：ChannelHandlerContext：上下文对象，含有 管道：pipeLine    通道channel     地址
     * @param msg：Object：客户端发送的消息
     * @throws Exception
     */
    /*@Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //读取从客户端发送的StudentPOJO.Student
        StudentPOJO.Student student = (StudentPOJO.Student) msg;
        System.out.println("客户端发送的数据 id"+student.getId()+"---name"+student.getName());
    }*/

    /**
     * 不需要强转
     * @param ctx
     * @param message
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MyDataInfo.MyMessage message) throws Exception {
        //根据dataType来显示不同的信息
        MyDataInfo.MyMessage.DateType dataType = message.getDataType();
        if (dataType == MyDataInfo.MyMessage.DateType.StudentType){
            System.out.println("客户端发送的数据 id"+message.getStudent().getId()+"---name"+message.getStudent().getName());
        }else if (dataType == MyDataInfo.MyMessage.DateType.WorkerType){
            System.out.println("客户端发送的数据 id"+message.getWorker().getAge()+"---name"+message.getWorker().getName());
        }else {
            System.out.println("类型传输错误");
        }
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
