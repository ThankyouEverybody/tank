package com.leo.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Leo
 * @ClassName Client
 * @DATE 2021/1/18 11:24 上午
 * @Description 客户端
 */
public class Client {
    private EventLoopGroup work;
    private Bootstrap bootstrap;
    public Client() {
        work = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
    }

    public void start(int port) {
        ChannelFuture f = null;
        try {
            f = bootstrap.group(work)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    .connect("localhost", port);

            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("connected!");
                    } else {
                        System.out.println("not connected!");
                    }
                }
            });
            f.sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            work.shutdownGracefully();
        }
    }


    private class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {
        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pip = ch.pipeline();
            pip.addLast(new ClientHandler());
        }
    }

    private class ClientHandler extends ChannelInboundHandlerAdapter {
        /**
         * 功能描述 : 处理连接成功
         * @author Leo
         * @date 2021/1/18 2:03 下午
         * @param ctx
         * @return void
         */
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            //ByteBuf效率高,因为直接访问操作系统内存,也就是堆外内存,Direct Memory
            //也就跳过了jvm垃圾回收机制
            ByteBuf buf = Unpooled.copiedBuffer("hello".getBytes());
            //writeAndFlush使用完了自动释放
            ctx.writeAndFlush(buf);
        }

        /**
         * 功能描述 : 处理读取
         * @author Leo
         * @date 2021/1/18 2:03 下午
         * @param ctx
         * @param msg
         * @return void
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            ByteBuf buf = null;
            try {
                //SimpleChannelInboundHandler和Codec结合使用有泛型
                buf = (ByteBuf) msg;
                byte[] bytes = new byte[buf.readableBytes()];
                buf.getBytes(buf.readerIndex(), bytes);
                System.out.println("Client : " + new String(bytes));
            } finally {
                if (buf != null) {
                    ReferenceCountUtil.release(buf);
                }
            }
        }
    }

}
