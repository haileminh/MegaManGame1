package com.hailm.megaman.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hailm.megaman.manager.GameManager;

public abstract class Human extends ParticularObject {

    private boolean isJumping;

    private boolean isDicking;

    private boolean isLanding;

    public Human(float posX, float posY, float width, float height, float mass,
            int blood, GameManager gameManager) {
        super(posX, posY, width, height, mass, blood, gameManager);

        setState(ALIVE);

    }

    public abstract void run();

    public abstract void jump();

    public abstract void dick();

    public abstract void standUp();

    public abstract void stopRun();

    @Override
    public void update() {
        super.update();

        if (getState() == ALIVE || getState() == NOBEHURT) {

            if (!isLanding) {

                if (!getIsDicking())
                    setPosX(getPosX() + getSpeedX());

                if (getDirection() == LEFT_DIR && getGameManager().physicalMap
                        .haveCollisionWithLeftWall(
                                getBoundForCollisionWithMap()) != null) {

                    Rectangle rectLeftWall = getGameManager().physicalMap
                            .haveCollisionWithLeftWall(
                                    getBoundForCollisionWithMap());
                    setPosX(rectLeftWall.x + rectLeftWall.width
                            + getWidth() / 2);

                }

                if (getDirection() == RIGHT_DIR && getGameManager().physicalMap
                        .haveCollisionWithRightWall(
                                getBoundForCollisionWithMap()) != null) {

                    Rectangle rectRightWall = getGameManager().physicalMap
                            .haveCollisionWithRightWall(
                                    getBoundForCollisionWithMap());
                    setPosX(rectRightWall.x - getWidth() / 2);

                }

                //
                //
                //

                Rectangle boundForCollisionWithMapFuture = getBoundForCollisionWithMap();
                boundForCollisionWithMapFuture.y += (getSpeedY() != 0
                        ? getSpeedY() : 2);
                Rectangle rectLand = getGameManager().physicalMap
                        .haveCollisionWithLand(boundForCollisionWithMapFuture);

                Rectangle rectTop = getGameManager().physicalMap
                        .haveCollisionWithTop(boundForCollisionWithMapFuture);

                if (rectTop != null) {

                    setSpeedY(0);
                    setPosY(rectTop.y
                            + getGameManager().physicalMap.getTileSize()
                            + getHeight() / 2);

                } else if (rectLand != null) {
                    setIsJumping(false);
                    if (getSpeedY() > 0)
                        setIsLanding(true);
                    setSpeedY(0);
                    setPosY(rectLand.y - getHeight() / 2 - 1);
                } else {
                    setIsJumping(true);
                    setSpeedY(getSpeedY() + getMass());
                    setPosY(getPosY() + getSpeedY());
                }
            }
        }
    }

    public boolean getIsJumping() {
        return isJumping;
    }

    public void setIsJumping(boolean isJumping) {
        this.isJumping = isJumping;
    }

    public boolean getIsDicking() {
        return isDicking;
    }

    public void setIsDicking(boolean isDicking) {
        this.isDicking = isDicking;
    }

    public void setIsLanding(boolean b) {
        isLanding = b;
    }

    public boolean getIsLanding() {
        return isLanding;
    }

    @Override
    public void attack() {
        // TODO Auto-generated method stub

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub

    }

}
