package com.leo.tank.observer;

import com.leo.tank.Tank;
import com.leo.tank.strategy.FireStrategy;

import java.util.EventObject;

/**
 * @author Leo
 * @ClassName TankFireEvent
 * @DATE 2021/1/11 7:54 下午
 * @Description 坦克开火事件
 */
public class TankFireEvent extends EventObject {
    private Tank tank;
    /**
     * Constructs a prototypical Event.
     */
    public TankFireEvent(FireStrategy fireStrategy,Tank tank) {
        super(fireStrategy);
        this.tank = tank;
    }
    public Tank getTank() {
        return tank;
    }
}
