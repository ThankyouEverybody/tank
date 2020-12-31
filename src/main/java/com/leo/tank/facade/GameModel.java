package com.leo.tank.facade;

import com.leo.tank.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 * @ClassName GameModel
 * @DATE 2020/12/31 11:09 上午
 * @Description 门面 facade
 */
public class GameModel {

    private static GameModel gameModel;

    public Tank myTank = new Tank(200, 400, Dir.DOWN, this, Group.GOOD);

    public List<Bullet> bullets = new ArrayList<>();

    public List<Tank> tanks = new ArrayList<>();

    public List<Explode> explodes = new ArrayList<>();

    /**
     * 功能描述 : 私有构造
     * @author Leo
     * @date 2020/12/31 11:13 上午
     */
    private GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tanks.add(new Tank(50 + i * 80, 50, Dir.DOWN, this, Group.BAD));
        }
    }

    /**
     * 功能描述 : DCL
     * @author Leo
     * @date 2020/12/31 11:13 上午
     * @return com.leo.tank.facade.GameModel
     */
    public static  GameModel getInstance(){
        if (gameModel == null) {
            synchronized (GameModel.class) {
                if (gameModel == null) {
                    gameModel = new GameModel();
                }
            }
        }
        return gameModel;
    }


    /**
     * 功能描述 :
     * @author Leo
     * @date 2020/12/31 11:13 上午
     * @param g
     * @throw
     * @return void
     */
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量:" + bullets.size(), 10, 60);
        g.drawString("敌人数量:" + tanks.size(), 10, 80);
        g.drawString("爆炸数量:" + explodes.size(), 10, 100);
        g.setColor(color);
        myTank.paint(g);

        /**
         * 这种方式在删除子弹的时候回出现
         * java.util.ConcurrentModificationException
         */
        /*for (Bullet bullet : bullets) {
            bullet.paint(g);
        }*/
        //解决方法1,使用普通for方式
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //解决方法2,使用迭代器自己的iterator.remove()
        /*for (Iterator<Bullet> iterator = bullets.iterator(); iterator.hasNext(); ) {
            Bullet next = iterator.next();
            if (!next.live) {
                iterator.remove();
            }
        }*/

        //敌人坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }

        //碰撞监测
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }
    }

    public Tank getMyTank() {
        return myTank;
    }
}
