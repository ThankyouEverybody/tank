package com.leo.tank;

import com.leo.tank.abstractfactory.BaseBullet;
import com.leo.tank.abstractfactory.BaseTank;
import com.leo.tank.abstractfactory.DefaultFactory;
import com.leo.tank.abstractfactory.GameFactory;

import java.awt.*;

/**
 * @author Leo
 * @ClassName Bullet
 * @DATE 2020/12/24 10:37 上午
 * @Description 炮弹
 */
public class Bullet extends BaseBullet {

    GameFactory gameFactory = DefaultFactory.getInstance();
    /**
     * 移动速度
     */
    private static final int SPEED = Integer.parseInt((String) (PropertyMgr.get("bulletSpeed")));
    /**
     * 宽度
     */
    public static final int WIDTH = ResourceMgr.bulletD.getWidth();
    /**
     * 高度
     */
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    /**
     * 位置坐标
     */
    private int x, y;
    /**
     * 方向
     */
    private Dir dir;
    /**
     * 是否存活
     */
    protected boolean live = true;
    /**
     * 当前frame的引用
     */
    private TankFrame tankFrame = null;
    /**
     * 子弹分组
     */
    protected Group group;
    /**
     * 用于碰撞检测
     */
    Rectangle rect = new Rectangle();

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        //每次new一个子弹的时候直接加入到bullets中.
        tankFrame.bullets.add(this);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public void paint(Graphics g) {
        if (!live) {
            tankFrame.bullets.remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
            default:
                break;
        }
        move();
    }

    private void move() {
        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        //update rect
        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || TankFrame.GAME_WIDTH < x || TankFrame.GAME_HEIGHT < y) {
            live = false;
        }
    }
    @Override
    public void collideWith(BaseTank tank) {
        if (this.group == tank.group) {
            return;
        }
        if (rect.intersects(tank.rect)) {
            this.die();
            tank.die();
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            tankFrame.explodes.add(gameFactory.createExplode(eX, eY, tankFrame));
        }
    }
    @Override
    protected void die() {
        this.live = false;
    }
}
