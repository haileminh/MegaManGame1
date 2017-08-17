package com.hailm.megaman.view;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;

import com.hailm.megaman.manager.CacheDataLoader;

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
