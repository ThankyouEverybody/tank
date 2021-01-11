package com.leo.tank.strategy;

import com.leo.tank.Audio;
import com.leo.tank.Bullet;
import com.leo.tank.Group;
import com.leo.tank.Tank;
import com.leo.tank.decorator.RectDecorator;
import com.leo.tank.decorator.TailDecorator;
import com.leo.tank.facade.GameModel;

/**
 * @author Leo
 * @ClassName DefaultFireStrategy
 * @DATE 2020/12/28 3:20 下午
 * @Description 普通子弹
 */
public class DefaultFireStrategy implements FireStrategy {
    private static DefaultFireStrategy fireStrategy;
    private DefaultFireStrategy() {

    }

    /**
     * 功能描述 : DCL
     * @author Leo
     * @date 2020/12/28 3:39 下午
     * @return com.leo.tank.strategy.DefaultFireStrategy
     */
    public static DefaultFireStrategy getInstance() {
        if (fireStrategy == null) {
            synchronized (DefaultFireStrategy.class) {
                if (fireStrategy == null) {
                    fireStrategy = new DefaultFireStrategy();
                }
            }
        }
        return fireStrategy;
    }


    @Override
    public void fire(Tank tank) {
        int bX = tank.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = tank.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        //BUG new Bullet又把自己添加了一遍
        new Bullet(bX, bY, tank.dir, tank.group);
        if (tank.group == Group.GOOD) {
            new Thread(() -> new Audio("audio/tank_fire.wav").play()).start();
        }
    }
}
