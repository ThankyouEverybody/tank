package com.leo.tank.cor;

import com.leo.tank.*;

/**
 * @author Leo
 * @ClassName BulletTankCollider
 * @DATE 2020/12/31 4:10 下午
 * @Description 负责子弹和墙碰撞  策略
 */
public class BulletWallCollider implements Collider {

    /**
     * 功能描述 : 子弹与墙相撞 碰撞检测
     * @author Leo
     * @date 2020/12/31 5:56 下午
     * @param o1
     * @param o2
     * @throw
     * @return void
     */
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall) o2;

            if (bullet.rect.intersects(wall.rect)) {
                bullet.die();
            }
        }else if (o2 instanceof Bullet && o1 instanceof Wall) {
            return collide(o2, o1);
        }
        return true;
    }
}
