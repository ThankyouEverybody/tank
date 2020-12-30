package com.leo.tank.strategy;

import com.leo.tank.*;
import com.leo.tank.abstractfactory.DefaultFactory;
import com.leo.tank.abstractfactory.GameFactory;

/**
 * @author Leo
 * @ClassName FourDirFireStrategy
 * @DATE 2020/12/28 3:41 下午
 * @Description 四个方向同时开火
 */
public class FourDirFireStrategy implements FireStrategy {

    private FourDirFireStrategy() {
    }

    private static class FourDirFireStrategyHolder {
        private final static FourDirFireStrategy fireStrategy = new FourDirFireStrategy();

    }
    public static FourDirFireStrategy getInstance(){
        return FourDirFireStrategyHolder.fireStrategy;
    }

    GameFactory gameFactory = DefaultFactory.getInstance();

    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = tank.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;

        Dir[] dirs = Dir.values();
        for(Dir dir : dirs) {
            gameFactory.createBullet(bX, bY, dir, tank.tankFrame, tank.group);
        }

        if(tank.group == Group.GOOD) {
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
