package com.leo.tank.decorator;

import com.leo.tank.GameObject;

import java.awt.*;

/**
 * @author Leo
 * @ClassName RectDecorator
 * @DATE 2021/1/11 5:14 下午
 * @Description 给GameObject包一层白色外边框
 */
public class RectDecorator extends GODecorator{

    public RectDecorator(GameObject gameObject) {
        super(gameObject);
    }

    @Override
    public void paint(Graphics g) {
        x = gameObject.x;
        y = gameObject.y;
        gameObject.paint(g);
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawRect(super.gameObject.x, super.gameObject.y, super.gameObject.getWidth() + 2, super.gameObject.getHeight() + 2);
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
