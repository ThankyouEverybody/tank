package com.leo.netty.server;

import com.leo.netty.group.Group;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * @author Leo
 * @ClassName Server
 * @DATE 2021/1/18 11:24 上午
 * @Description 服务端
 */
public class Server {



    private EventLoopGroup boss;
    private EventLoopGroup work;
    private ServerBootstrap bootstrap;
    public Server(){
        boss = new NioEventLoopGroup(1);
        work = new NioEventLoopGroup(2);
        bootstrap = new ServerBootstrap();
    }

    public void start(int port) {
        try {
            ChannelFuture f = bootstrap.group(boss, work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyServerHandler())
                    .bind(port)
                    .sync();

            System.out.println("Server started!");

            //获取通道,等待调用close方法,也就是等待关闭,如果没人关闭,会一直等待(阻塞)
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            work.shutdownGracefully();
            boss.shutdownGracefully();
        }
    }

    private class MyServerHandler extends ChannelInitializer<SocketChannel> {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            //责任链模式处理读写.在pip添加处理器
            ChannelPipeline pip = ch.pipeline();
            pip.addLast(new ServerChildHandler());


        }
    }

    private class ServerChildHandler extends ChannelInboundHandlerAdapter {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            //加入到通道组.
            Group.getInstance().add(ctx.channel());
        }

        /**
         * 功能描述 : 处理读取
         * @author Leo
         * @date 2021/1/18 2:44 下午
         * @param ctx
         * @param msg
         * @return void
         */
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
            ByteBuf buf = null;
            try {
                //SimpleChannelInboundHandler和Codec结合使用有泛型
                buf = (ByteBuf) msg;
                byte[] bytes = new byte[buf.readableBytes()];
                buf.getBytes(buf.readerIndex(), bytes);
                System.out.println("Server : " + new String(bytes));
                //给所有通道组写出数据
                Group.getInstance().writeAndFlush(Unpooled.copiedBuffer("helloClient".getBytes()));
            } finally {
                if (buf != null) {
                    ReferenceCountUtil.release(buf);
                }
            }
        }

        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            cause.printStackTrace();
            ctx.close();
        }
    }

}
