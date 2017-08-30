package com.hailm.megaman.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hailm.megaman.manager.Animation;
import com.hailm.megaman.manager.CacheDataLoader;
import com.hailm.megaman.manager.GameManager;

public class RedEyeDevil extends ParticularObject {
    private Animation forwardAnim, backAnim;

    private long startTimeToShoot;

    public RedEyeDevil(float posX, float posY, GameManager gameManager) {
        super(posX, posY, 127, 89, 0, 100, gameManager);
        setDamage(10);
        setTimeForNoBehurt(300000000);
        backAnim = CacheDataLoader.getInstance().getAnimation("redeye");
        forwardAnim = CacheDataLoader.getInstance().getAnimation("redeye");
        forwardAnim.flipAllImage();
        startTimeToShoot = 0;
    }

    @Override
    public void attack() {
        Bullet bullet = new RedEyeBullet(getPosX(), getPosY(),
                getGameManager());
        if (getDirection() == LEFT_DIR) {
            bullet.setSpeedX(-8);
        } else {
            bullet.setSpeedX(8);
        }

        bullet.setTeamType(getTeamType());
        getGameManager().bulletManager.addObject(bullet);
    }

    @Override
    public void update() {
        super.update();
        if (System.nanoTime() - startTimeToShoot > 1000 * 1000000) {
            attack();
            System.out.println("red eye attack");
            startTimeToShoot = System.nanoTime();
        }
    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        Rectangle rect = getBoundForCollisionWithMap();
        rect.x += 20;
        rect.width -= 40;

        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {
        if (!isObjectOutOfCameraView()) {
            if (getState() == NOBEHURT
                    && (System.nanoTime() / 1000000) % 2 != 1) {

            } else {
                if (getDirection() == LEFT_DIR) {
                    backAnim.update(System.nanoTime());
                    backAnim.draw(g2,
                            (int) (getPosX()
                                    - getGameManager().camera.getPosX()),
                            (int) (getPosY()
                                    - getGameManager().camera.getPosY()));
                } else {
                    forwardAnim.update(System.nanoTime());
                    forwardAnim.draw(g2,
                            (int) (getPosX()
                                    - getGameManager().camera.getPosX()),
                            (int) (getPosY()
                                    - getGameManager().camera.getPosY()));
                }
            }
        }
    }

}
