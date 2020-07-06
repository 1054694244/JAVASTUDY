package com.shenzc.netty.inboundandoutbound;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

/**
 * @author shenzc
 * @create 2020-07-04-10:01
 */
public class MyClientHandler extends SimpleChannelInboundHandler<Long> {

    Long msg = null;

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Long aLong) throws Exception {
        System.out.println("服务的读取的数据是"+aLong);
    }

    /**
     * 发送数据
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("MyClientHandler发送数据");
        /**
         * 分析
         * 1：“abcdabcdabcdabcd”是16个字节
         * 2：myLongToByteEncoder父类是MessageToByteEncoder
         * 3：父类通过判断msg是不是该处理的类型，如果是就处理，不是就跳过encode，我们在编写encode的时候要注意传入的类型，和处理的类型要一直
         */
        ctx.writeAndFlush(new Long(123456));
        //ctx.writeAndFlush(Unpooled.copiedBuffer("abcdabcdabcdabcdabcdabcdabcdabcd", CharsetUtil.UTF_8));
    }
}
