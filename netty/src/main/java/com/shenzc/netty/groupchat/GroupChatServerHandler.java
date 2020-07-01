package com.shenzc.netty.groupchat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class GroupChatServerHandler extends SimpleChannelInboundHandler<String> {

    //定义一个channel组，管理所有的channel
    //GlobalEventExecutor.INSTANCE是一个全局事件执行器，是一个单例
    private static ChannelGroup channelGroup= new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    /**
     * 表示连接建立，一旦连接建立，第一个被执行
     * 将当前这个channel加入到channelGroup中
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        //将该客户加入到聊天系统中推送给其他客户端
        /**
         * 该方法会将channelGroup所有的channel遍历并发送此消息，所以我们不需要自己遍历
         */
        channelGroup.writeAndFlush("客户端"+channel.remoteAddress()+"加入聊天\n");
        channelGroup.add(channel);
    }

    /**
     * 断开连接会被触发,将xx客户端信息推送给当前在线的客户
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("客户端"+channel.remoteAddress()+"离开了");
        System.out.println("channelGroup size"+channelGroup.size());
    }

    /**
     * 表示channel处于一个活动状态，提示xx上线
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"上线了");
    }

    /**
     * 表示channel处于不活动状态，提示xx下线了
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println(ctx.channel().remoteAddress()+"下线了");
    }

    /**
     * 读取数据
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        //获取到当前channel
        Channel channel = ctx.channel();
        //这时候w我们遍历channelGroup，根据情况的不同，会送不同的消息
        channelGroup.forEach(ch -> {
            //不是当前的channel，转发消息
            if (ch != channel){
                ch.writeAndFlush("客户"+channel.remoteAddress()+"发送消息"+msg+"\n");
            }else {
                ch.writeAndFlush("自己发送了消息"+msg);
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println(cause);
        ctx.close();
    }
}
