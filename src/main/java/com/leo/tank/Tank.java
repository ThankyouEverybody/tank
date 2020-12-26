package com.leo.tank;

import java.awt.*;
import java.util.Random;


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
    private boolean moving = true;
    /**
     * 宽度
     */
    public static final int WIDTH  = ResourceMgr.goodTankL.getWidth();
    /**
     * 高度
     */
    public static final int HEIGHT = ResourceMgr.goodTankL.getHeight();
    /**
     * 是否存活
     */
    protected boolean live = true;
    /**
     * 当前frame的引用.
     */
    private TankFrame tankFrame = null;
    /**
     * 随机换方向
     */
    private Random random = new Random();
    /**
     * 坦克分组
     */
    protected Group group;

    public Tank(int x, int y, Dir dir, TankFrame tankFrame, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tankFrame = tankFrame;
        this.group = group;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }



    /**
     * 画坦克
     */
    public void paint(Graphics g) {
        if (!live) {
            tankFrame.tanks.remove(this);
            return;
        }
        switch(dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
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
            case LEFT:
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
        if (this.group == Group.BAD && random.nextInt(10) > 8) {
            this.fire();
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
        tankFrame.bullets.add(new Bullet(bX, bY, this.dir, tankFrame, this.group));
        if(this.group == Group.GOOD) {
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }
    }

    public void die() {
        this.live = false;
    }
}
