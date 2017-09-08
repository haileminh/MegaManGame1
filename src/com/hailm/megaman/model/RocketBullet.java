package com.hailm.megaman.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hailm.megaman.manager.Animation;
import com.hailm.megaman.manager.CacheDataLoader;
import com.hailm.megaman.manager.GameManager;

public class RocketBullet extends Bullet {

    private Animation bulletForwardAnimUp, bulletForwardAnimDown,
            bulletForwardAnim;

    private Animation bulletBackAnimUp, bulletBackAnimDown, bulletBackAnim;

    private long startTimeForChangeSpeedY;

    public RocketBullet(float posX, float posY, GameManager gameManager) {
        super(posX, posX, 30, 30, 1.0f, 10, gameManager);
        bulletBackAnimUp = CacheDataLoader.getInstance()
                .getAnimation("rocketUp");
        bulletBackAnimDown = CacheDataLoader.getInstance()
                .getAnimation("rocketDown");
        bulletBackAnim = CacheDataLoader.getInstance().getAnimation("rocket");

        bulletForwardAnimUp = CacheDataLoader.getInstance()
                .getAnimation("rocketUp");
        bulletForwardAnimUp.flipAllImage();
        bulletForwardAnimDown = CacheDataLoader.getInstance()
                .getAnimation("rocketDown");
        bulletForwardAnimDown.flipAllImage();
        bulletForwardAnim = CacheDataLoader.getInstance()
                .getAnimation("rocket");
        bulletForwardAnim.flipAllImage();
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (getSpeedX() > 0) {
            if (getSpeedY() > 0) {
                bulletForwardAnimDown.draw(g2d,
                        (int) (getPosX() - getGameManager().camera.getPosX()),
                        (int) (getPosY() - getGameManager().camera.getPosY()));
            } else if (getSpeedY() < 0) {
                bulletForwardAnimUp.draw(g2d,
                        (int) (getPosX() - getGameManager().camera.getPosX()),
                        (int) (getPosY() - getGameManager().camera.getPosY()));
            } else {
                bulletForwardAnim.draw(g2d,
                        (int) (getPosX() - getGameManager().camera.getPosX()),
                        (int) (getPosY() - getGameManager().camera.getPosY()));
            }
        } else {
            if (getSpeedY() > 0) {
                bulletBackAnimDown.draw(g2d,
                        (int) (getPosX() - getGameManager().camera.getPosX()),
                        (int) (getPosY() - getGameManager().camera.getPosY()));
            } else if (getSpeedY() < 0) {
                bulletBackAnimUp.draw(g2d,
                        (int) (getPosX() - getGameManager().camera.getPosX()),
                        (int) (getPosY() - getGameManager().camera.getPosY()));
            } else {
                bulletBackAnim.draw(g2d,
                        (int) (getPosX() - getGameManager().camera.getPosX()),
                        (int) (getPosY() - getGameManager().camera.getPosY()));
            }
        }
    }

    @Override
    public void update() {
        super.update();

        if (System.nanoTime() - startTimeForChangeSpeedY > 500 * 1000000) {
            startTimeForChangeSpeedY = System.nanoTime();
        }
    }

    @Override
    public void attack() {
        // TODO Auto-generated method stub

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        return getBoundForCollisionWithMap();
    }

}
