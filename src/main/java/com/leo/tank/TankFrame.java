package com.leo.tank;

import com.leo.tank.strategy.DefaultFireStrategy;
import com.leo.tank.strategy.FourDirFireStrategy;

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

    GameModel gameModel = GameModel.getInstance();



    static final int GAME_WIDTH = Integer.parseInt((String) (PropertyMgr.get("gameWidth")));

    static final int GAME_HEIGHT = Integer.parseInt((String) (PropertyMgr.get("gameHeight")));

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
        gameModel.paint(g);
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
                    gameModel.getMyTank().fire(DefaultFireStrategy.getInstance());
                    break;
                case KeyEvent.VK_META:
                    gameModel.getMyTank().fire(FourDirFireStrategy.getInstance());
                    break;
                case KeyEvent.VK_CONTROL:
                    gameModel.getMyTank().fire(FourDirFireStrategy.getInstance());
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bU && !bR && !bD) {
                gameModel.getMyTank().setMoving(false);
                return;
            }
            gameModel.getMyTank().setMoving(true);

            if (bL) {
                gameModel.getMyTank().setDir(Dir.LEFT);
            }
            if (bU) {
                gameModel.getMyTank().setDir(Dir.UP);
            }
            if (bR) {
                gameModel.getMyTank().setDir(Dir.RIGHT);
            }
            if (bD) {
                gameModel.getMyTank().setDir(Dir.DOWN);
            }

        }
    }


}
