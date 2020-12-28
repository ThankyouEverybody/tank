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
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        //初始化敌方坦克
        for (int i = 0; i < initTankCount; i++) {
            tankFrame.tanks.add(new Tank(50 + i * 80, 50, Dir.DOWN, tankFrame,Group.BAD));
        }

        //music
//        new Thread(()->new Audio("audio/war1.wav").loop()).start();
        while (true) {
            try {
                Thread.sleep(25);
                tankFrame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
