package com.leo.tank;

import java.awt.*;

/**
 * @author Leo
 * @ClassName Wall
 * @DATE 2021/1/4 3:28 下午
 * @Description 墙
 */
public class Wall extends GameObject {

    /**
     * 宽高
     */
    public int w,h;

    public Rectangle rect;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        rect = new Rectangle(x, y, w, h);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(x, y, w, h);
        g.setColor(c);
    }
}
