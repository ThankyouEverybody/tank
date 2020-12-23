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
        this.setSize(800,600);
        this.setResizable(false);
        this.setTitle("tank war");
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        g.fillRect(200,200,50,50);
    }
}
