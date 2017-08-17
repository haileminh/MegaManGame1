package com.hailm.megaman.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.hailm.megaman.manager.Animation;
import com.hailm.megaman.manager.FrameImage;

public class GamePanel extends BasePanel implements Runnable {
    private boolean IS_RUNNING;

    private Thread thread;

    private KeyListener keyListener;

    private InputManager inputManager;

    FrameImage fram1, frame2, fram3;

    Animation animation;

    public GamePanel() {
        inputManager = new InputManager();

        BufferedImage image;
        try {
            image = ImageIO
                    .read(new File("src/res/drawable/images/megasprite.png"));
            BufferedImage image1 = image.getSubimage(529, 40, 80, 100);
            fram1 = new FrameImage("frame1", image1);

            BufferedImage image2 = image.getSubimage(616, 40, 80, 100);
            frame2 = new FrameImage("frame2", image2);

            BufferedImage image3 = image.getSubimage(704, 40, 80, 100);
            fram3 = new FrameImage("frame3", image3);

            animation = new Animation();
            animation.add(fram1, 200 * 1000000);
            animation.add(frame2, 200 * 1000000);
            animation.add(fram3, 200 * 1000000);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

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
        Graphics2D graphics2d = (Graphics2D) g;

        graphics2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        
        animation.update(System.nanoTime());
        animation.draw(graphics2d, 100, 130);
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
            // render game
            repaint();
            
            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;

            try {
                if (sleepTime > 0) {
                    Thread.sleep(sleepTime / 1000000);
                } else {
                    Thread.sleep(sleepTime / 2000000);
                }

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            beginTime = System.nanoTime();
        }
    }

}
