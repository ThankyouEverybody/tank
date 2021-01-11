package com.leo.tank;

import com.leo.tank.facade.GameModel;

import java.awt.*;

/**
 * @author Leo
 * @ClassName Bullet
 * @DATE 2020/12/24 10:37 上午
 * @Description 爆炸
 */
public class Explode extends GameObject {

    /**
     * 宽度
     */
    public static final int WIDTH = ResourceMgr.explodes[0].getWidth();
    /**
     * 高度
     */
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    /**
     * 是否存活
     */
    protected boolean live = true;

    private int step = 0;

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
//        new Thread(() -> new Audio("audio/explode.wav").play()).start();
        //门面
        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            /**
             * 调停者
             */
            GameModel.getInstance().remove(this);
        }

    }


}
