package com.leo.tank.cor;

import com.leo.tank.Bullet;
import com.leo.tank.Explode;
import com.leo.tank.GameObject;
import com.leo.tank.Tank;
import com.leo.tank.facade.GameModel;

/**
 * @author Leo
 * @ClassName BulletTankCollider
 * @DATE 2020/12/31 4:10 下午
 * @Description 负责碰撞  策略
 */
public class BulletTankCollider implements Collider {

    /**
     * 功能描述 : 子弹与坦克相撞 碰撞检测
     * @author Leo
     * @date 2020/12/31 5:56 下午
     * @param o1
     * @param o2
     * @throw
     * @return void
     */
    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
            if (bullet.group == tank.group) {
                return true;
            }
            if (bullet.rect.intersects(tank.rect)) {
                bullet.die();
                tank.die();
                int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
                int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
                bullet.gameModel.add(new Explode(eX, eY, tank.gameModel));
                //已经撞上了 就不继续检测坦克之间的碰撞
                return false;
            }
            return true;
        }else if (o2 instanceof Bullet && o1 instanceof Tank) {
            return collide(o2, o1);
        }else {
            return true;
        }
    }
}
