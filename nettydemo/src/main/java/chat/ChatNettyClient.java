package chat;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;
import java.util.Scanner;

public class ChatNettyClient {
    public static void main(String[] args) throws InterruptedException, IOException {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class)
                    // 客户端只有一个eventLoopGroup，所以不需要用childHandler
                    .handler(new ChatNettyClientHandler());

            // 在这里发送，在MyChatClientHandler中接收服务端的返回
            Channel channel = bootstrap.connect("localhost", 9000).sync().channel();
            Scanner scan = new Scanner(System.in);
            while (scan.hasNext()) {
                channel.writeAndFlush(scan.next() + "\r\n");
            }
        } finally {
            eventLoopGroup.shutdownGracefully();
        }
    }
}