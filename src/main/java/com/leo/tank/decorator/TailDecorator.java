package com.leo.tank.decorator;

import com.leo.tank.GameObject;

import java.awt.*;

/**
 * @author Leo
 * @ClassName RectDecorator
 * @DATE 2021/1/11 5:14 下午
 * @Description
 */
public class TailDecorator extends GODecorator{

    public TailDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {
        x = gameObject.x;
        y = gameObject.y;
        gameObject.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(gameObject.x, gameObject.y, gameObject.x + getWidth(), gameObject.y + getHeight());
        g.setColor(c);
    }

    @Override
    public int getWidth() {
        return super.gameObject.getWidth();
    }

    @Override
    public int getHeight() {
        return super.gameObject.getHeight();
    }
}
