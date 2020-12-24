package com.leo.tank;

import com.leo.tank.TankFrame;

/**
 * @author Leo
 * @ClassName Main
 * @DATE 2020/12/23 8:15 下午
 * @Description
 */
public class Main {

    public static void main(String[] args){
        TankFrame tankFrame = new TankFrame();

        //初始化敌方坦克
        for (int i = 0; i < 5; i++) {
            tankFrame.tanks.add(new Tank(50 + i * 80, 200, Dir.DOWN, tankFrame));
        }

        while (true) {
            try {
                Thread.sleep(50);
                tankFrame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
