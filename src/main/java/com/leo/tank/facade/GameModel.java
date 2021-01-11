package com.leo.tank.facade;

import com.leo.tank.*;
import com.leo.tank.cor.BulletTankCollider;
import com.leo.tank.cor.Collider;
import com.leo.tank.cor.ColliderChain;
import com.leo.tank.cor.TankTankCollider;
import com.leo.tank.decorator.RectDecorator;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 * @ClassName GameModel
 * @DATE 2020/12/31 11:09 上午
 * @Description
 * 由tankFrame角度看,GameModel是门面(facade)
 * 由GameModel的子类角度看,GameModel是调(tiao)停者
 */
public class GameModel {

    private static GameModel gameModel;

    static {
        init();
    }

    public static Tank myTank;

    /* 由调停者 gameObjectList 代替
    public List<Bullet> bullets = new ArrayList<>();

    public List<Tank> tanks = new ArrayList<>();

    public List<Explode> explodes = new ArrayList<>();*/

    private List<GameObject> gameObjectList = new ArrayList<>();

    /**
     * 碰撞责任链
     */
    private ColliderChain colliderChain = new ColliderChain();

    /**
     * 功能描述 : 私有构造
     *
     * @author Leo
     * @date 2020/12/31 11:13 上午
     */
    private GameModel() {

    }


    private static void init() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
        myTank = new Tank(200, 400, Dir.DOWN, Group.GOOD);

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            new Tank(50 + i * 80, 50, Dir.DOWN, Group.BAD);
        }

        //初始化墙
        gameModel.add(new Wall(150, 150, 200, 50));
        gameModel.add(new Wall(550, 150, 200, 50));
        gameModel.add(new Wall(300, 300, 50, 200));
        gameModel.add(new Wall(550, 300, 50, 200));
    }
    /**
     * 功能描述 : DCL
     *
     * @return com.leo.tank.facade.GameModel
     * @author Leo
     * @date 2020/12/31 11:13 上午
     */
    public static GameModel getInstance() {
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
     * @param g
     * @return void
     * @author Leo
     * @date 2020/12/31 11:13 上午
     * @throw
     */
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        /*g.drawString("子弹数量:" + bullets.size(), 10, 60);
        g.drawString("敌人数量:" + tanks.size(), 10, 80);
        g.drawString("爆炸数量:" + explodes.size(), 10, 100);*/
        g.setColor(color);
//        myTank.paint(g);


        //敌人坦克
        for (int i = 0; i < gameObjectList.size(); i++) {
            gameObjectList.get(i).paint(g);
        }

        //碰撞监测
        for (int i = 0; i < gameObjectList.size(); i++) {
            for (int j = i + 1; j < gameObjectList.size(); j++) {
                GameObject o1 = gameObjectList.get(i);
                GameObject o2 = gameObjectList.get(j);
                colliderChain.collide(o1, o2);
            }
        }
    }
    /**
     * 功能描述 : 获取主坦克
     * @author Leo
     * @date 2020/12/31 3:57 下午
     * @return com.leo.tank.Tank
     */
    public Tank getMyTank() {
        return myTank;
    }
    /**
     * 功能描述 : 删除GameObject
     * @author Leo
     * @date 2020/12/31 3:58 下午
     * @param o
     * @return void
     */
    public void remove(GameObject o) {
        gameObjectList.remove(o);
    }
    /**
     * 功能描述 : 添加GameObject
     * @author Leo
     * @date 2020/12/31 3:59 下午
     * @param o
     * @return void
     */
    public void add(GameObject o) {
        gameObjectList.add(o);
    }


}
