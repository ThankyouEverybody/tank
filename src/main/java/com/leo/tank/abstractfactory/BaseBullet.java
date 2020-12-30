package com.leo.tank.abstractfactory;

import com.leo.tank.Tank;

import java.awt.*;

/**
 * @author Leo
 * @ClassName BaseBullet
 * @DATE 2020/12/28 9:12 下午
 * @Description 子弹抽象类
 */
public abstract class BaseBullet {

    /**
     * 功能描述 : 画子弹
     * @author Leo
     * @date 2020/12/28 9:15 下午
     * @param g
     * @return void
     */
    public abstract void paint(Graphics g);

    /**
     * 功能描述 : 子弹与坦克相撞 碰撞检测
     * @author Leo
     * @date 2020/12/28 9:15 下午
     * @param tank
     * @return void
     */
    public abstract void collideWith(BaseTank tank);

    protected abstract void die();
}
