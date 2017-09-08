package com.hailm.megaman.view;

import java.awt.CardLayout;
import java.io.IOException;

import javax.swing.JFrame;

import com.hailm.megaman.manager.CacheDataLoader;

public class Gui extends JFrame implements SetUp, OnPanelStateListener {
    private GamePanel gamePanel;

    private MenuPanel menuPanel;

    public static final int WIDTH_FRAME = 1000;

    public static final int HEIGHT_FRAME = 600;

    public static final int PLAYGAME = 1;

    public Gui() {
        initcomponents();
        addComponents();
        registerListener();
    }

    @Override
    public void initcomponents() {
        setTitle("Megaman Game");
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

        // gamePanel = new GamePanel();
        // add(gamePanel);
        menuPanel = new MenuPanel();
        menuPanel.setOnPanelStateListener(this);
        add(menuPanel);

    }

    @Override
    public void registerListener() {
        // TODO Auto-generated method stub

    }

    @Override
    public void switchPanel(int a) {
        switch (a) {
        case PLAYGAME:
            remove(menuPanel);
            gamePanel = new GamePanel();
            add(gamePanel);
            revalidate();
            gamePanel.setFocus();
            
            startGame();
            break;

        default:
            break;
        }

    }


}
