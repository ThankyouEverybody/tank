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

        //music
        //new Thread(()->new Audio("audio/war1.wav").loop()).start();
        while (true) {
            try {
                Thread.sleep(30);
                tankFrame.repaint();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
