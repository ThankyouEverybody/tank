package com.leo.tank.abstractfactory;

import com.leo.tank.*;

/**
 * @author Leo
 * @ClassName DefaultFactory
 * @DATE 2020/12/28 9:29 下午
 * @Description 默认的游戏工厂
 */
public class DefaultFactory extends GameFactory {


    private static DefaultFactory factory;

    private DefaultFactory() {

    }

    /**
     * 功能描述 : DCL 单例
     * @author Leo
     * @date 2020/12/28 9:31 下午
     * @return com.leo.tank.abstractfactory.DefaultFactory
     */
    public static DefaultFactory getInstance() {
        if (factory == null) {
            synchronized (DefaultFactory.class) {
                if (factory == null) {
                    factory = new DefaultFactory();
                }
            }
        }
        return factory;
    }
    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        return new Tank(x, y, dir, tankFrame,group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tankFrame) {
        return new Explode(x, y, tankFrame);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        return new Bullet(x, y, dir, tankFrame, group);
    }
}
