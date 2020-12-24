package com.leo.tank;

import com.leo.tank.TankFrame;

import java.awt.*;


/**
 * @author Leo
 * @ClassName Tank
 * @DATE 2020/12/24 9:54 上午
 * @Description 坦克封装
 */
public class Tank {
    /**
     * 位置坐标
     */
    private int  x, y;
    /**
     * 方向
     */
    private Dir dir = Dir.DOWN;
    /**
     * 移动速度
     */
    private static final int SPEED = 5;
    /**
     * 是否移动
     */
    private boolean moving = false;
    /**
     * 宽度
     */
    public static final int WIDTH  = ResourceMgr.tankL.getWidth();
    /**
     * 高度
     */
    public static final int HEIGHT = ResourceMgr.tankL.getHeight();
    /**
     * 当前frame的引用.
     */
    private TankFrame tankFrame = null;

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank(int x, int y, Dir dir, TankFrame tankFrame) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
    }

    /**
     * 画坦克
     */
    public void paint(Graphics g) {
        switch (dir) {
            case Left:
                g.drawImage(ResourceMgr.tankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.tankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.tankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.tankD, x, y, null);

                break;
            default:
                break;
        }
        move(dir);
    }
    /**
     * 坦克移动
     */
    private void move(Dir dir) {
        if (!moving) {
            return;
        }
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
    }

    /**
     * 功能描述 : 开火
     * @author Leo
     * @date 2020/12/24 2:04 下午
     * @return void
     */
    public void fire() {
        int bX = this.x + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
        int bY = this.y + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
        tankFrame.bullets.add(new Bullet(bX, bY, this.dir, tankFrame));
    }
}
