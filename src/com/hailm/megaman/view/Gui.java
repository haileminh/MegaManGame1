package com.hailm.megaman.view;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;

import com.hailm.megaman.manager.CacheDataLoader;

public class Gui extends JFrame implements SetUp {
    private GamePanel gamePanel;

    public static final int WIDTH_FRAME = 1000;

    public static final int HEIGHT_FRAME = 600;

    public Gui() {
        initcomponents();
        addComponents();
        registerListener();
    }

    @Override
    public void initcomponents() {
        setTitle("Test Game.");
        setLayout(new CardLayout());
        setResizable(false);
        setSize(WIDTH_FRAME, HEIGHT_FRAME);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void startGame() {
        gamePanel.startGame();
    }

    @Override
    public void addComponents() {
        
        try {
            CacheDataLoader.getInstance().loadData();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        gamePanel = new GamePanel();
        add(gamePanel);
    }

    @Override
    public void registerListener() {
        // TODO Auto-generated method stub

    }

}
