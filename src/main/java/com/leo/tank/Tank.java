package com.leo.tank;

import com.leo.tank.facade.GameModel;
import com.leo.tank.strategy.DefaultFireStrategy;
import com.leo.tank.strategy.FireStrategy;

import java.awt.*;
import java.util.Random;


/**
 * @author Leo
 * @ClassName Tank
 * @DATE 2020/12/24 9:54 上午
 * @Description 坦克封装
 */
public class Tank extends GameObject {

    /**
     * 方向
     */
    public Dir dir = Dir.DOWN;
    /**
     * 移动速度
     */
    private static final int SPEED = Integer.parseInt((String) (PropertyMgr.get("tankSpeed")));
    /**
     * 是否移动
     */
    private boolean moving = true;
    /**
     * 宽度
     */
    public static final int WIDTH = ResourceMgr.goodTankL.getWidth();
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
    public TankFrame tankFrame = null;
    /**
     * 随机换方向
     */
    private Random random = new Random();
    /**
     * 坦克分组
     */
    public Group group;
    /**
     * 用于碰撞检测
     */
    public Rectangle rect = new Rectangle();

    private int oldX,oldY;
    public Tank(int x, int y, Dir dir,Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
        //门面
        GameModel.getInstance().add(this);
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

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    /**
     * 画坦克
     */
    @Override
    public void paint(Graphics g) {
        if (!live) {
            /**
             * 调停者
             */
            GameModel.getInstance().remove(this);
        }
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }
        move(dir);
    }

    /**
     * 坦克移动
     */
    private void move(Dir dir) {
        //记录之前的位置
        oldX = this.x;
        oldY = this.y;
        if (!moving) {
            return;
        }
        if (!live) {
            return;
        }

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


        if (this.group == Group.BAD && random.nextInt(10) > 8) {
            this.fire(DefaultFireStrategy.getInstance());

        }
        if (this.group == Group.BAD && random.nextInt(100) > 95) {
            randomDir();
        }
        boundsCheck();

        //update  rect
        rect.x = this.x;
        rect.y = this.y;
    }

    /**
     * 功能描述 : 回到之前的位置
     * @author Leo
     * @date 2021/1/4 3:27 下午
     * @return void
     */
    public void back() {
        this.x = oldX;
        this.y = oldY;
    }

    /**
     * 功能描述 : 坦克边界监测
     *
     * @param
     * @return void
     * @author Leo
     * @date 2020/12/26 9:12 下午
     * @throw
     */
    private void boundsCheck() {
        if (this.x < 0) {
            x = 0;
        }
        if (this.y < 20) {
            y = 20;
        }
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT;
        }

    }

    /**
     * 功能描述 : 随机方向
     *
     * @return void
     * @author Leo
     * @date 2020/12/26 8:57 下午
     */
    public void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    /**
     * 功能描述 : 开火
     *
     * @param fireStrategy
     * @return void
     * @author Leo
     * @date 2020/12/24 2:04 下午
     */
    public void fire(FireStrategy fireStrategy) {
        fireStrategy.fire(this);
    }

    public void die() {
        this.live = false;
    }


}
