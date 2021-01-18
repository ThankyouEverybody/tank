package com.leo.netty.group;

import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author Leo
 * @ClassName Group
 * @DATE 2021/1/18 4:04 下午
 * @Description 用于记录连接过得通道组,实现转发同步数据.
 */
public class Group {
    private static ChannelGroup group;


    private Group() {

    }

    public static ChannelGroup getInstance() {
        if (group == null) {
            synchronized (Group.class) {
                if (group == null) {
                    group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
                }
            }
        }
        return group;
    }
}
