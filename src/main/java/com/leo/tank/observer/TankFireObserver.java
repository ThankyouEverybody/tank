package com.leo.tank.observer;

import java.io.Serializable;

/**
 * @author Leo
 * @ClassName TankFireObserver
 * @DATE 2021/1/11 7:54 下午
 * @Description Observer 观察者模式
 * 15
 */
public interface TankFireObserver extends Serializable {
    /**
     * 功能描述 : 观察者模式开火
     * @author Leo
     * @date 2021/1/11 7:59 下午
     * @param e
     * @return void
     */
    void actionFire(TankFireEvent e);
}
