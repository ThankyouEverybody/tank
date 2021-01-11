package com.leo.tank.observer;

import com.leo.tank.strategy.FireStrategy;

/**
 * @author Leo
 * @ClassName TankFireHandler
 * @DATE 2021/1/11 7:55 下午
 * @Description 观察者模式实现类
 */
public class TankFireHandler implements TankFireObserver {
    @Override
    public void actionFire(TankFireEvent e) {
        FireStrategy f = (FireStrategy) e.getSource();
        f.fire(e.getTank());
    }
}
