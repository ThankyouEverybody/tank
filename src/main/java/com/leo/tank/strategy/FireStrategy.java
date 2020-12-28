package com.leo.tank.strategy;

import com.leo.tank.Tank;

/**
 * @author Leo
 * @ClassName FireStrategy
 * @DATE 2020/12/28 3:20 下午
 * @Description fire接口
 */
public interface FireStrategy {

    /**
     * 功能描述 : 开火
     * @author Leo
     * @date 2020/12/28 3:38 下午
     * @param tank
     * @return void
     */
    void fire(Tank tank);
}
