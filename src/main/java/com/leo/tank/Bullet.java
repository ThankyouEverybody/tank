package com.leo.tank;

import com.leo.tank.TankFrame;

import java.awt.*;

/**
 * @author Leo
 * @ClassName Bullet
 * @DATE 2020/12/24 10:37 上午
 * @Description 炮弹
 */
public class Bullet {

    /**
     * 移动速度
     */
    private static final int SPEED = 15;
    /**
     * 宽度
     */
    public static final int WIDTH  = ResourceMgr.bulletD.getWidth();
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

    public Bullet(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }


    public void paint(Graphics g) {
        if (!live) {
            tankFrame.bullets.remove(this);
        }
        switch (dir) {
            case Left:
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
            case Left:
                x -= SPEED;
                break;
            case UP:
                y -=SPEED;
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
        if (x < 0 || y < 0 || TankFrame.GAME_WIDTH < x || TankFrame.GAME_HEIGHT < y) {
            live = false;
        }
    }
    /**
     * 子弹与坦克相撞
     * 碰撞检测
     */
    public void collideWith(Tank tank) {
        if (this.group == tank.group) {
            return;
        }

        Rectangle rectBullet = new Rectangle(this.x, this.y, WIDTH, HEIGHT);
        Rectangle rectTank = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);
        if (rectBullet.intersects(rectTank)) {
            this.die();
            tank.die();
        }
    }

    private void die() {
        this.live = false;
    }
}
