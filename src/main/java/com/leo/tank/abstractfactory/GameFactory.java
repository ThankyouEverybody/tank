package com.leo.tank.abstractfactory;

import com.leo.tank.Dir;
import com.leo.tank.Group;
import com.leo.tank.TankFrame;

/**
 * @author Leo
 * @ClassName GameFactory
 * @DATE 2020/12/28 9:04 下午
 * @Description 游戏工厂
 */
public abstract class GameFactory {

    /**
     * 功能描述 : 创建坦克
     * @author Leo
     * @date 2020/12/28 9:26 下午
     * @param x
     * @param y
     * @param dir
     * @param tankFrame
     * @param group
     * @return com.leo.tank.abstractfactory.BaseTank
     */
    public abstract BaseTank createTank(int x, int y, Dir dir, TankFrame tankFrame, Group group);

    /**
     * 功能描述 : 创建爆炸
     * @param x
     * @param y
     * @param tankFrame
     * @return com.leo.tank.abstractfactory.BaseExplode
     * @author Leo
     * @date 2020/12/28 9:26 下午
     */
    public abstract BaseExplode createExplode(int x, int y, TankFrame tankFrame);

    /**
     * 功能描述 : 创建子弹
     * @author Leo
     * @date 2020/12/28 9:28 下午
     * @param x
     * @param y
     * @param dir
     * @param tankFrame
     * @param group
     * @return com.leo.tank.abstractfactory.BaseBullet
     */
    public abstract BaseBullet createBullet(int x, int y, Dir dir, TankFrame tankFrame, Group group);
}
