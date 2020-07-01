package com.shenzc.netty.hearteat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartServerHandler extends ChannelInboundHandlerAdapter {

    /**
     * 处理心跳事件
     * @param ctx：上下文
     * @param evt：事件
     * @throws Exception
     */
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            //强转,向下转型
            IdleStateEvent idleStateEvent = (IdleStateEvent)evt;
            //定义事件类型
            String eventType = null;
            switch (idleStateEvent.state()){
                case ALL_IDLE:
                    eventType = "读写空闲";
                    break;
                case READER_IDLE:
                    eventType = "读空闲";
                    break;
                case WRITER_IDLE:
                    eventType = "写空闲";
                    break;
            }
            System.out.println(ctx.channel().remoteAddress()+"超时事件发生:"+eventType);
            System.out.println("服务器做相应的处理");

            //如果空闲了，我们关闭通道
            ctx.channel().close();
        }
    }
}
