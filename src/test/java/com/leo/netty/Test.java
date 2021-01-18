package com.leo.netty;

import com.leo.netty.client.Client;
import com.leo.netty.server.Server;

/**
 * @author Leo
 * @ClassName Test
 * @DATE 2021/1/18 11:36 上午
 * @Description 16-3
 */
public class Test {

    @org.junit.Test
    public void ServerTest() {
        new Server().start(8888);
    }

    @org.junit.Test
    public void ClientTest() {
        new Client().start(8888);
    }




}
