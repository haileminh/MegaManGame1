package com.hailm.megaman.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hailm.megaman.manager.Animation;
import com.hailm.megaman.manager.CacheDataLoader;
import com.hailm.megaman.manager.GameManager;

public class FinalBoss extends Human {

    private Animation idleForwardAnim, idleBackAnim;

    public FinalBoss(float posX, float posY, GameManager gameManager) {
        super(posX, posY, 110, 150, 0.1f, 100, gameManager);

        idleForwardAnim = CacheDataLoader.getInstance()
                .getAnimation("boss_idle");
        idleBackAnim = CacheDataLoader.getInstance().getAnimation("boss_idle");
        idleBackAnim.flipAllImage();

    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        idleForwardAnim.update(System.nanoTime());
        idleForwardAnim.draw(g2,
                (int) (getPosX() - getGameManager().camera.getPosX()),
                (int) (getPosY() - getGameManager().camera.getPosY()));
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub

    }

    @Override
    public void jump() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dick() {
        // TODO Auto-generated method stub

    }

    @Override
    public void standUp() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stopRun() {
        // TODO Auto-generated method stub

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return super.getBoundForCollisionWithMap();
    }

}
