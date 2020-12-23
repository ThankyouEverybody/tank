package com.leo.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author Leo
 * @ClassName TankFrame
 * @DATE 2020/12/23 8:26 下午
 * @Description
 */
public class TankFrame extends Frame {

    public TankFrame() {

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

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
    }
}
