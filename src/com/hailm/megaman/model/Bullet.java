package com.hailm.megaman.model;

import java.awt.Graphics2D;

import com.hailm.megaman.manager.GameManager;

public abstract class Bullet extends ParticularObject {

    public Bullet(float posX, float posY, float width, float height, float mass,
            int damage, GameManager gameManager) {
        super(posX, posY, width, height, mass, 1, gameManager);
        setDamage(damage);
    }

    public abstract void draw(Graphics2D g2d);

    @Override
    public void update() {
        super.update();

        setPosX(getPosX() + getSpeedX());
        setPosY(getPosY() + getSpeedY());

        ParticularObject object = getGameManager().objectManager
                .getCollisionWidthEnemyObject(this);
        if (object != null && object.getState() == ALIVE) {
            setBlood(0);
            object.beHurt(getDamage());
            System.out.println("Bullet set behurt for e");
        }

    }
}
