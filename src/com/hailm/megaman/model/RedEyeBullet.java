package com.hailm.megaman.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hailm.megaman.manager.Animation;
import com.hailm.megaman.manager.CacheDataLoader;
import com.hailm.megaman.manager.GameManager;

public class RedEyeBullet extends Bullet {
    private Animation bulletForwardAnim, bulletBackAnim;

    public RedEyeBullet(float posX, float posY, GameManager gameManager) {
        super(posX, posY, 30, 30, 1.0f, 10, gameManager);
        
        bulletForwardAnim = CacheDataLoader.getInstance()
                .getAnimation("redeyebullet");
        bulletBackAnim = CacheDataLoader.getInstance()
                .getAnimation("redeyebullet");
        bulletBackAnim.flipAllImage();
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (getSpeedX() > 0) {
            bulletForwardAnim.update(System.nanoTime());
            bulletForwardAnim.draw(g2d,
                    (int) (getPosX() - getGameManager().camera.getPosX()),
                    (int) getPosY() - (int) getGameManager().camera.getPosY());
        } else {
            bulletBackAnim.update(System.nanoTime());
            bulletBackAnim.draw(g2d,
                    (int) (getPosX() - getGameManager().camera.getPosX()),
                    (int) getPosY() - (int) getGameManager().camera.getPosY());
        }
    }

    @Override
    public void attack() {
        // TODO Auto-generated method stub
    }

    @Override
    public void update() {
        super.update();
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

}
