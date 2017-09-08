package com.hailm.megaman.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hailm.megaman.manager.Animation;
import com.hailm.megaman.manager.CacheDataLoader;
import com.hailm.megaman.manager.GameManager;

public class MonsterDevil extends ParticularObject {
    private Animation forwardAnim, backAnim;

    private long startTimeToShoot;

    private float x1, x2;

    public MonsterDevil(float posX, float posY, GameManager gameManager) {
        super(posX, posY, 127, 89, 0, 100, gameManager);
        setTimeForNoBehurt(300000000);

        x1 = posX - 100;
        x2 = posY + 100;
        setSpeedX(1);
        setDamage(20);
        forwardAnim = CacheDataLoader.getInstance().getAnimation("darkraise");
        backAnim = CacheDataLoader.getInstance().getAnimation("darkraise");
        backAnim.flipAllImage();
    }

    @Override
    public void attack() {
        float megaManX = getGameManager().megaman.getPosX();
        float megaManY = getGameManager().megaman.getPosY();

        float deltaX = megaManX - getPosX();
        float deltaY = megaManY - getPosY();

        float speed = 3;
        float a = Math.abs(deltaX / deltaY);

        float speedX = (float) Math.sqrt(speed * speed * a * a / (a * a + 1));
        float speedY = (float) Math.sqrt(speed * speed / (a * a + 1));

        Bullet bullet = new MonsterBullet(getPosX(), getPosY(),
                getGameManager());

        if (deltaX < 0)
            bullet.setSpeedX(-speedX);
        else
            bullet.setSpeedX(speedX);
        bullet.setSpeedY(speedY);
        bullet.setTeamType(getTeamType());
        getGameManager().bulletManager.addObject(bullet);
    }

    @Override
    public void update() {
        super.update();
        if (getPosX() < x1)
            setSpeedX(1);
        else if (getPosX() > x2)
            setSpeedX(-1);
        setPosX(getPosX() + getSpeedX());

        if (System.nanoTime() - startTimeToShoot > 1000 * 10000000 * 1.5) {
            attack();
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
                // plash
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
