package com.leo.tank;

import com.leo.tank.abstractfactory.BaseExplode;

import java.awt.*;

/**
 * @author Leo
 * @ClassName Bullet
 * @DATE 2020/12/24 10:37 上午
 * @Description 爆炸
 */
public class Explode extends BaseExplode {

    /**
     * 宽度
     */
    public static final int WIDTH  = ResourceMgr.explodes[0].getWidth();
    /**
     * 高度
     */
    public static final int HEIGHT = ResourceMgr.explodes[0].getHeight();
    /**
     * 位置坐标
     */
    private int x, y;
    /**
     * 是否存活
     */
    protected boolean live = true;
    /**
     * 当前frame的引用
     */
    private TankFrame tankFrame = null;

    private int step = 0;
    public Explode(int x, int y, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.tankFrame = tankFrame;
//        new Thread(() -> new Audio("audio/explode.wav").play()).start();

        ;
    }


    @Override
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
            tankFrame.explodes.remove(this);
        }

    }


}
