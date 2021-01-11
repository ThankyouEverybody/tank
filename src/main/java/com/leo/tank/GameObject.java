package com.leo.tank;

import com.leo.tank.observer.TankFireEvent;

import java.awt.*;

/**
 * @author Leo
 * @ClassName GameObject
 * @DATE 2020/12/31 3:44 下午
 * @Description Mediator 调停者
 *
 */
public abstract class GameObject {

    /**
     * 位置坐标
     */
    public int x, y;

    /**
     * 功能描述 :
     * @author Leo
     * @date 2020/12/31 3:51 下午
     * @param g
     * @return void
     */
    public abstract void paint(Graphics g);

    /**
     * 功能描述 : getWidth
     * @return int
     * @author Leo
     * @date 2021/1/11 5:32 下午
     */
    public abstract int getWidth();

    /**
     * 功能描述 : getHeight
     *
     * @return int
     * @author Leo
     * @date 2021/1/11 5:32 下午
     */
    public abstract int getHeight();
}
