package com.leo.tank.decorator;

import com.leo.tank.GameObject;

import java.awt.*;

/**
 * @author Leo
 * @ClassName GODecorator
 * @DATE 2021/1/11 5:11 下午
 * @Description 装饰模式
 */
public abstract class GODecorator extends GameObject {
    GameObject gameObject;

    public GODecorator(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    @Override
    public abstract void paint(Graphics g);
}
