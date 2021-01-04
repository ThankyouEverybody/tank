package com.leo.tank.cor;

import com.leo.tank.GameObject;
import com.leo.tank.Tank;

/**
 * @author Leo
 * @ClassName TankTankCollider
 * @DATE 2020/12/31 6:01 下午
 * @Description 坦克直接的碰撞检测
 */
public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank t1 = (Tank) o1;
            Tank t2 = (Tank) o2;
            if (t1.rect.intersects(t2.rect)) {
                t1.back();
                t2.back();
            }
        }
        return true;

    }
}
