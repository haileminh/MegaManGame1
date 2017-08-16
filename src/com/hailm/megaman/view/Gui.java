package com.hailm.megaman.view;

import java.awt.CardLayout;

import javax.swing.JFrame;

public class Gui extends JFrame implements SetUp {

    public static final int WIDTH_FRAME = 1000;

    public static final int HEIGHT_FRAME = 600;

    private GamePanel gamePanel;

    public Gui() {
        initsComponents();
        addComponents();
        registerListener();
    }

    @Override
    public void initsComponents() {
        setTitle("Test game");
        setResizable(false);
        setLayout(new CardLayout());
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    @Override
    public void addComponents() {
        gamePanel = new GamePanel();
        add(gamePanel);
    }

    @Override
    public void registerListener() {
        // TODO Auto-generated method stub

    }

}
