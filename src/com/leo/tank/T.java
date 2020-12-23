package com.leo.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Leo
 * @ClassName T
 * @DATE 2020/12/23 8:15 下午
 * @Description
 */
public class T {

    public static void main(String[] args){
        Frame f = new Frame();
        f.setSize(800,600);
        f.setResizable(false);
        f.setTitle("tank war");
        f.setVisible(true);
        f.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                System.exit(0);
            }
        });

    }
}
