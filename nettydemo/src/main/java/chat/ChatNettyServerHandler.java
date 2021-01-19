package chat;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * 自定义Handler需要继承netty规定好的某个HandlerAdapter(规范)
 */
public class ChatNettyServerHandler extends SimpleChannelInboundHandler<String> {
    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);


    //该方法发出的消息，自身channel还未形成所以不能收到消息
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器消息】" + channel.remoteAddress() + "上线了\n");

        channelGroup.add(channel);
        System.out.println("【服务器消息】" + channel.remoteAddress() + "上线了");
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("【服务器消息】" + channel.remoteAddress() + "下线了\n");

        channelGroup.remove(channel);
        System.out.println("【服务器消息】" + channel.remoteAddress() + "下线了");
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println("【服务器消息】收到一条消息：" + msg);
        Channel channel = channelHandlerContext.channel();
        channelGroup.forEach(ch -> {
            if (channel == ch) {
                ch.writeAndFlush("[自己]：" + msg + "\n");
            } else {
                ch.writeAndFlush("[" + channel.remoteAddress() + "]：" + msg + "\n");
            }
        });
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}