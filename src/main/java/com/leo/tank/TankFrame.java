package com.leo.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Leo
 * @ClassName TankFrame
 * @DATE 2020/12/23 8:26 下午
 * @Description 封装继承多态 哈哈
 */
public class TankFrame extends Frame {
    Tank myTank = new Tank(200, 400, Dir.DOWN, this);
    List<Bullet> bullets = new ArrayList<>();

    List<Tank> tanks = new ArrayList<>();
    static final int GAME_WIDTH = 800;

    static final int GAME_HEIGHT = 600;

    public TankFrame() {
        this.setSize(GAME_WIDTH,GAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new MyKeyListener());
    }

    Image offScreenImage = null;

    /**
     * 双缓冲解决屏幕闪烁问题.
     * update在paint之前调用.
     *
     * 截获update
     * 首先把该画出来的东西（坦克， 子弹）先画在内存的图片中，图片大小和游戏画面一致
     * 把内存中图片一次性画到屏幕上（内存的内容复制到显存）
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量:" + bullets.size(), 10, 60);
        g.drawString("敌人数量:" + tanks.size(), 10, 90);
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
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

    }

    /**
     * 键盘监听
     */
    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bU = false;
        boolean bR = false;
        boolean bD = false;

        /**
         * 功能描述 : onkeydown
         * @author Leo
         * @date 2020/12/23 9:01 下午
         * @param e
         * @return void
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;

            }
            setMainTankDir();
        }

        /**
         * 功能描述 : onkeyup
         * @author Leo
         * @date 2020/12/23 9:00 下午
         * @param e
         * @return void
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {

            if (!bL && !bU && !bR && !bD) {
                myTank.setMoving(false);
                return;
            }

            myTank.setMoving(true);

            if (bL) {
                myTank.setDir(Dir.Left);
            }
            if (bU) {
                myTank.setDir(Dir.UP);
            }
            if (bR) {
                myTank.setDir(Dir.RIGHT);
            }
            if (bD) {
                myTank.setDir(Dir.DOWN);
            }

        }
    }


}
