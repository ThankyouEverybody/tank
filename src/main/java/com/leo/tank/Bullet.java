package com.leo.tank;

import com.leo.tank.facade.GameModel;

import java.awt.*;

/**
 * @author Leo
 * @ClassName Bullet
 * @DATE 2020/12/24 10:37 上午
 * @Description 炮弹
 */
public class Bullet extends GameObject{

    /**
     * 移动速度
     */
    private static final int SPEED = Integer.parseInt((String) (PropertyMgr.get("bulletSpeed")));
    /**
     * 宽度
     */
    public static final int WIDTH  = ResourceMgr.bulletD.getWidth();
    /**
     * 高度
     */
    public static final int HEIGHT = ResourceMgr.bulletD.getHeight();
    /**
     * 方向
     */
    private Dir dir;
    /**
     * 是否存活
     */
    protected boolean live = true;
    /**
     * 子弹分组
     */
    public Group group;
    /**
     * 用于碰撞检测
     */
    public Rectangle rect = new Rectangle();
    /**
     * 门面
     */
    public GameModel gameModel;

    public Bullet(int x, int y, Dir dir, GameModel gm, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.gameModel = gm;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        //每次new一个子弹的时候直接加入到bullets中.
        gameModel.add(this);
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
            gameModel.remove(this);
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

        //update rect
        rect.x = this.x;
        rect.y = this.y;

        if (x < 0 || y < 0 || TankFrame.GAME_WIDTH < x || TankFrame.GAME_HEIGHT < y) {
            live = false;
        }
    }


    public void die() {
        this.live = false;
    }
}
