package com.hailm.megaman.view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import com.hailm.megaman.manager.Animation;
import com.hailm.megaman.manager.CacheDataLoader;
import com.hailm.megaman.manager.FrameImage;
import com.hailm.megaman.manager.GameManager;
import com.hailm.megaman.model.MegaMan;
import com.hailm.megaman.model.PhysicalMap;

public class GamePanel extends BasePanel implements Runnable {

    private boolean IS_RUNNING;

    private Thread thread;

    private KeyListener keyListener;

    private InputManager inputManager;

    private BufferedImage bufImage;

    private Graphics2D bufG2D;

    public GameManager gameManager;

    FrameImage frame1;

    Animation animation1;

    public GamePanel() {
        gameManager = new GameManager();
        inputManager = new InputManager(gameManager);
        bufImage = new BufferedImage(Gui.WIDTH_FRAME, Gui.HEIGHT_FRAME,
                BufferedImage.TYPE_INT_ARGB);

        frame1 = CacheDataLoader.getInstance().getFrameImage("idleshoot1");
        animation1 = CacheDataLoader.getInstance().getAnimation("boss_idle");
        animation1.flipAllImage();
    }

    @Override
    public void initcomponents() {
        setLayout(null);
        setBackground(Color.WHITE);
    }

    @Override
    public void addComponents() {
        // TODO Auto-generated method stub

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

    public void updateGame() {
        gameManager.update();
    }

    public void renderGame() {
        if (bufImage == null) {
            bufImage = new BufferedImage(Gui.WIDTH_FRAME, Gui.HEIGHT_FRAME,
                    BufferedImage.TYPE_INT_ARGB);
        }

        if (bufImage != null) {
            bufG2D = (Graphics2D) bufImage.getGraphics();
        }

        if (bufG2D != null) {

            bufG2D.setColor(Color.WHITE);
            bufG2D.fillRect(0, 0, Gui.WIDTH_FRAME, Gui.HEIGHT_FRAME);

            // draw objects game here
            // megaman.draw(bufG2D);

            // frame1.draw(bufG2D, 100, 130);
            // animation1.update(System.nanoTime());
            // animation1.draw(bufG2D, 300, 300);

            gameManager.render(bufG2D);

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
        long FPS = 80;
        long period = 1000 * 1000000 / FPS;
        long beginTime;
        long sleepTime;

        beginTime = System.nanoTime();
        while (IS_RUNNING) {

            updateGame();
            renderGame();

            repaint();

            long deltaTime = System.nanoTime() - beginTime;
            sleepTime = period - deltaTime;

            try {
                if (sleepTime > 0)
                    Thread.sleep(sleepTime / 1000000);
                else
                    Thread.sleep(period / 2000000);
            } catch (InterruptedException ex) {
            }

            beginTime = System.nanoTime();

        }
    }

}
