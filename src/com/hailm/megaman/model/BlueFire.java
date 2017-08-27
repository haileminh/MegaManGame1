package com.hailm.megaman.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hailm.megaman.manager.Animation;
import com.hailm.megaman.manager.CacheDataLoader;
import com.hailm.megaman.manager.GameManager;

public class BlueFire extends Bullet {

    private Animation bulletForwardAnim, bulletBackAnim;

    public BlueFire(float posX, float posY, GameManager gameManager) {
        super(posX, posY, 60, 30, 1.0f, 10, gameManager);

        bulletForwardAnim = CacheDataLoader.getInstance()
                .getAnimation("bluefire");
        bulletBackAnim = CacheDataLoader.getInstance().getAnimation("bluefire");
        bulletBackAnim.flipAllImage();

    }

    @Override
    public void draw(Graphics2D g2d) {
        if (getSpeedX() > 0) {
            if (!bulletForwardAnim.isIgnoreFrame(0)
                    && bulletForwardAnim.getCurrentFrame() == 3) {
                bulletForwardAnim.setIgnoreFrame(0);
                bulletForwardAnim.setIgnoreFrame(1);
                bulletForwardAnim.setIgnoreFrame(2);
            }

            bulletForwardAnim.update(System.nanoTime());
            bulletForwardAnim.draw(g2d,
                    (int) (getPosX() - getGameManager().camera.getPosX()),
                    (int) (getPosY() - getGameManager().camera.getPosY()));

        } else {
            if (!bulletBackAnim.isIgnoreFrame(0)
                    && bulletBackAnim.getCurrentFrame() == 3) {
                bulletBackAnim.setIgnoreFrame(0);
                bulletBackAnim.setIgnoreFrame(1);
                bulletBackAnim.setIgnoreFrame(2);
            }
            bulletBackAnim.update(System.nanoTime());
            bulletBackAnim.draw(g2d,
                    (int) (getPosX() - getGameManager().camera.getPosX()),
                    (int) (getPosY() - getGameManager().camera.getPosY()));
        }
    }

    @Override
    public void update() {
        super.update();
        if (bulletForwardAnim.isIgnoreFrame(0)
                || bulletBackAnim.isIgnoreFrame(0)) {
            setPosX(getPosX() + getSpeedX());
        }
    }

    @Override
    public void attack() {
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

}
