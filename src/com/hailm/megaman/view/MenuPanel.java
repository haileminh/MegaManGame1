package com.hailm.megaman.view;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.hailm.megaman.manager.CacheDataLoader;

public class MenuPanel extends BasePanel {

    private JButton btnPlayGame;

    private JButton btnExit;

    @Override
    public void initcomponents() {
        setBackground(Color.BLUE);
        setLayout(null);
    }

    @Override
    public void addComponents() {
        btnPlayGame = new JButton("PLAY GAME");
        btnPlayGame.setBounds(450, 250, 128, 32);
        add(btnPlayGame);

        btnExit = new JButton("EXIT");
        btnExit.setBounds(450, 300, 128, 32);
        add(btnExit);
    }

    @Override
    public void registerListener() {
        btnPlayGame.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                onPanelStateListener.switchPanel(Gui.PLAYGAME);
            }
        });

        btnExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent arg0) {
                System.exit(0);
            }
        });
    }

}
