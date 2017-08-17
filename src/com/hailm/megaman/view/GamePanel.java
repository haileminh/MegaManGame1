package com.hailm.megaman.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends BasePanel implements Runnable {
    private boolean IS_RUNNING;

    private Thread thread;

    private KeyListener keyListener;

    private InputManager inputManager;

    private BufferedImage bufImage;

    private Graphics2D bufG2d;

    public GamePanel() {
        inputManager = new InputManager();

        bufImage = new BufferedImage(Gui.WIDTH_FRAME, Gui.HEIGHT_FRAME,
                BufferedImage.TYPE_INT_ARGB);

    }

    @Override
    public void initsComponents() {
        setLayout(null);
        setBackground(Color.WHITE);
    }

    @Override
    public void addComponents() {

        startGame();
    }

    @Override
    public void registerListener() {
        keyListener = new KeyListener() {

            @Override
            public void keyPressed(KeyEvent e) {
                inputManager.processKeyPressed(e.getKeyCode());
            }

            @Override
            public void keyReleased(KeyEvent e) {
                inputManager.processKeyReleased(e.getKeyCode());
            }

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
            }

        };
        setFocusable(true);
        addKeyListener(keyListener);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Graphics2D graphics2d = (Graphics2D) g;
        
        g.drawImage(bufImage, 0, 0, this);
    }

    public void renderGame() {
        if (bufImage == null) {
            bufImage = new BufferedImage(Gui.WIDTH_FRAME, Gui.HEIGHT_FRAME,
                    BufferedImage.TYPE_INT_ARGB);
        }

        if (bufImage != null) {
            bufG2d = (Graphics2D) bufImage.getGraphics();
        }

        if (bufG2d != null) {
            bufG2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            bufG2d.setColor(Color.RED);
            bufG2d.fillRect(40, 50, 100, 100);
        }
    }

    public void startGame() {
        if (thread == null) {
            IS_RUNNING = true;
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {

        long FPS = 80; // số frame trên 1s
        long period = 1000 * 1000000 / FPS;
        long beginTime;
        long sleepTime;

        beginTime = System.nanoTime();
        while (IS_RUNNING) {

            // update game
            
            renderGame();

            repaint();

            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;

            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime / 1000000);
                } else {
                    Thread.sleep(period / 2000000);
                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            beginTime = System.nanoTime();
        }
    }

}
