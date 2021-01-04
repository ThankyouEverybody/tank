package com.leo.tank.cor;

import com.leo.tank.GameObject;
import com.leo.tank.Group;
import com.leo.tank.Tank;
import com.leo.tank.Wall;
import com.sun.org.apache.regexp.internal.RE;

/**
 * @author Leo
 * @ClassName TankTankCollider
 * @DATE 2020/12/31 6:01 下午
 * @Description 坦克与墙碰撞检测
 */
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;
            if (tank.rect.intersects(wall.rect)) {
                tank.back();
            }
        }else if (o2 instanceof Tank && o1 instanceof Wall) {
            return this.collide(o2, o1);
        }
        return true;

    }
}
