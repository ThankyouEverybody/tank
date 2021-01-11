package com.leo.tank.cor;

import com.leo.tank.GameObject;

/**
 * @author Leo
 * @ClassName Collider
 * @DATE 2020/12/31 4:08 下午
 * @Description 碰撞器
 *
 */
public interface Collider {

    /**
     * 功能描述 : 碰撞
     * @author Leo
     * @date 2020/12/31 4:09 下午
     * @param o1
     * @param o2
     * @return boolean true继续 false停止
     */
    boolean collide(GameObject o1, GameObject o2);
}
