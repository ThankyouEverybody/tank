package com.leo.tank.abstractfactory;

import com.leo.tank.Dir;
import com.leo.tank.Group;
import com.leo.tank.strategy.DefaultFireStrategy;
import com.leo.tank.strategy.FireStrategy;

import java.awt.*;

/**
 * @author Leo
 * @ClassName BaseTank
 * @DATE 2020/12/28 9:05 下午
 * @Description
 */
public abstract class BaseTank {
    public Group group = Group.BAD;
    /**
     * 用于碰撞检测
     */
    public Rectangle rect = new Rectangle();

    /**
     * 功能描述 : 获取分组
     * @author Leo
     * @date 2020/12/28 9:11 下午
     * @return com.leo.tank.Group
     */
    public Group getGroup() {
        return this.group;
    }
    /**
     * 功能描述 : 画坦克
     * @author Leo
     * @date 2020/12/28 9:10 下午
     * @param graphics
     * @return void
     */
    public abstract void paint(Graphics graphics);
    /**
     * 功能描述 : tank die
     * @author Leo
     * @date 2020/12/28 9:10 下午
     * @return void
     */
    public abstract void die();
    /**
     * 功能描述 : getX
     * @author Leo
     * @date 2020/12/28 9:11 下午
     * @return int
     */
    public abstract int getX();
    /**
     * 功能描述 : getY
     * @author Leo
     * @date 2020/12/28 9:11 下午
     * @return int
     */
    public abstract int getY();

    /**
     * 功能描述 : 开火
     * @author Leo
     * @date 2020/12/28 9:35 下午
     * @param instance
     * @return void
     */
    public abstract void fire(FireStrategy instance);

    /**
     * 功能描述 : setDir
     * @author Leo
     * @date 2020/12/28 9:36 下午
     * @param dir
     * @return void
     */
    public abstract void setDir(Dir dir);

    /**
     * 功能描述 : setMoving
     * @author Leo
     * @date 2020/12/28 9:36 下午
     * @param b
     * @throw
     * @return void
     */
    public abstract void setMoving(boolean b);
}
