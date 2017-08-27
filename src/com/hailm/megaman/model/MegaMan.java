package com.hailm.megaman.model;

import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hailm.megaman.manager.Animation;
import com.hailm.megaman.manager.CacheDataLoader;
import com.hailm.megaman.manager.GameManager;

public class MegaMan extends Human {
    public static final int RUNSPEED = 3;

    private Animation runForwardAnim, runBackAnim, runShootingForwarAnim,
            runShootingBackAnim;

    private Animation idleForwardAnim, idleBackAnim, idleShootingForwardAnim,
            idleShootingBackAnim;

    private Animation dickForwardAnim, dickBackAnim;

    private Animation flyForwardAnim, flyBackAnim, flyShootingForwardAnim,
            flyShootingBackAnim;

    private Animation landingForwardAnim, landingBackAnim;

    private Animation climWallForward, climWallBack;

    private long lastShootingTime;

    private boolean isShooting = false;

    public MegaMan(float x, float y, GameManager gameManager) {
        super(x, y, 70, 90, 0.1f, 100, gameManager);

        setTeamType(LEAGUE_TEAM);

        setTimeForNoBehurt(2000 * 1000000);

        runForwardAnim = CacheDataLoader.getInstance().getAnimation("run");
        runBackAnim = CacheDataLoader.getInstance().getAnimation("run");
        runBackAnim.flipAllImage();

        idleForwardAnim = CacheDataLoader.getInstance().getAnimation("idle");
        idleBackAnim = CacheDataLoader.getInstance().getAnimation("idle");
        idleBackAnim.flipAllImage();

        dickForwardAnim = CacheDataLoader.getInstance().getAnimation("dick");
        dickBackAnim = CacheDataLoader.getInstance().getAnimation("dick");
        dickBackAnim.flipAllImage();

        flyForwardAnim = CacheDataLoader.getInstance().getAnimation("flyingup");
        flyForwardAnim.setIsRepeated(false);
        flyBackAnim = CacheDataLoader.getInstance().getAnimation("flyingup");
        flyBackAnim.setIsRepeated(false);
        flyBackAnim.flipAllImage();

        landingForwardAnim = CacheDataLoader.getInstance()
                .getAnimation("landing");
        landingBackAnim = CacheDataLoader.getInstance().getAnimation("landing");
        landingBackAnim.flipAllImage();

        climWallBack = CacheDataLoader.getInstance().getAnimation("clim_wall");
        climWallForward = CacheDataLoader.getInstance()
                .getAnimation("clim_wall");
        climWallForward.flipAllImage();

        behurtForwardAnim = CacheDataLoader.getInstance()
                .getAnimation("hurting");
        behurtBackAnim = CacheDataLoader.getInstance().getAnimation("hurting");
        behurtBackAnim.flipAllImage();

        idleShootingForwardAnim = CacheDataLoader.getInstance()
                .getAnimation("idleshoot");
        idleShootingBackAnim = CacheDataLoader.getInstance()
                .getAnimation("idleshoot");
        idleShootingBackAnim.flipAllImage();

        runShootingForwarAnim = CacheDataLoader.getInstance()
                .getAnimation("runshoot");
        runShootingBackAnim = CacheDataLoader.getInstance()
                .getAnimation("runshoot");
        runShootingBackAnim.flipAllImage();

        flyShootingForwardAnim = CacheDataLoader.getInstance()
                .getAnimation("flyingupshoot");
        flyShootingBackAnim = CacheDataLoader.getInstance()
                .getAnimation("flyingupshoot");
        flyShootingBackAnim.flipAllImage();

    }

    @Override
    public void update() {

        super.update();

        if (isShooting) {
            if (System.nanoTime() - lastShootingTime > 500 * 1000000) {
                isShooting = false;
            }
        }

        if (getIsLanding()) {
            landingBackAnim.update(System.nanoTime());
            if (landingBackAnim.isLastFrame()) {
                setIsLanding(false);
                landingBackAnim.reset();
                runForwardAnim.reset();
                runBackAnim.reset();
            }
        }

    }

    @Override
    public Rectangle getBoundForCollisionWithEnemy() {
        // TODO Auto-generated method stub
        Rectangle rect = getBoundForCollisionWithMap();

        if (getIsDicking()) {
            rect.x = (int) getPosX() - 22;
            rect.y = (int) getPosY() - 20;
            rect.width = 44;
            rect.height = 65;
        } else {
            rect.x = (int) getPosX() - 22;
            rect.y = (int) getPosY() - 40;
            rect.width = 44;
            rect.height = 80;
        }

        return rect;
    }

    @Override
    public void draw(Graphics2D g2) {

        switch (getState()) {

        case ALIVE:
        case NOBEHURT:
            if (getState() == NOBEHURT
                    && (System.nanoTime() / 10000000) % 2 != 1) {
                System.out.println("Plash...");
            } else {

                if (getIsLanding()) {

                    if (getDirection() == RIGHT_DIR) {
                        landingForwardAnim.setCurrentFrame(
                                landingBackAnim.getCurrentFrame());
                        landingForwardAnim.draw(g2,
                                (int) (getPosX()
                                        - getGameManager().camera.getPosX()),
                                (int) getPosY()
                                        - (int) getGameManager().camera
                                                .getPosY()
                                        + (getBoundForCollisionWithMap().height
                                                / 2
                                                - landingForwardAnim
                                                        .getCurrentImage()
                                                        .getHeight() / 2));
                    } else {
                        landingBackAnim.draw(g2,
                                (int) (getPosX()
                                        - getGameManager().camera.getPosX()),
                                (int) getPosY()
                                        - (int) getGameManager().camera
                                                .getPosY()
                                        + (getBoundForCollisionWithMap().height
                                                / 2
                                                - landingBackAnim
                                                        .getCurrentImage()
                                                        .getHeight() / 2));
                    }

                } else if (getIsJumping()) { 

                    if (getDirection() == RIGHT_DIR) {
                        flyForwardAnim.update(System.nanoTime());
                        if (isShooting) {
                            flyShootingForwardAnim.setCurrentFrame(
                                    flyForwardAnim.getCurrentFrame());
                            flyShootingForwardAnim.draw(g2,
                                    (int) (getPosX()
                                            - getGameManager().camera.getPosX())
                                            + 10,
                                    (int) getPosY()
                                            - (int) getGameManager().camera
                                                    .getPosY());
                        } else
                            flyForwardAnim.draw(g2,
                                    (int) (getPosX() - getGameManager().camera
                                            .getPosX()),
                                    (int) getPosY()
                                            - (int) getGameManager().camera
                                                    .getPosY());
                    } else {
                        flyBackAnim.update(System.nanoTime());
                        if (isShooting) {
                            flyShootingBackAnim.setCurrentFrame(
                                    flyBackAnim.getCurrentFrame());
                            flyShootingBackAnim.draw(g2,
                                    (int) (getPosX()
                                            - getGameManager().camera.getPosX())
                                            - 10,
                                    (int) getPosY()
                                            - (int) getGameManager().camera
                                                    .getPosY());
                        } else
                            flyBackAnim.draw(g2,
                                    (int) (getPosX() - getGameManager().camera
                                            .getPosX()),
                                    (int) getPosY()
                                            - (int) getGameManager().camera
                                                    .getPosY());
                    }

                } else if (getIsDicking()) {

                    if (getDirection() == RIGHT_DIR) {
                        dickForwardAnim.update(System.nanoTime());
                        dickForwardAnim.draw(g2,
                                (int) (getPosX()
                                        - getGameManager().camera.getPosX()),
                                (int) getPosY()
                                        - (int) getGameManager().camera
                                                .getPosY()
                                        + (getBoundForCollisionWithMap().height
                                                / 2
                                                - dickForwardAnim
                                                        .getCurrentImage()
                                                        .getHeight() / 2));
                    } else {
                        dickBackAnim.update(System.nanoTime());
                        dickBackAnim.draw(g2,
                                (int) (getPosX()
                                        - getGameManager().camera.getPosX()),
                                (int) getPosY()
                                        - (int) getGameManager().camera
                                                .getPosY()
                                        + (getBoundForCollisionWithMap().height
                                                / 2
                                                - dickBackAnim.getCurrentImage()
                                                        .getHeight() / 2));
                    }

                } else {
                    if (getSpeedX() > 0) {
                        runForwardAnim.update(System.nanoTime());
                        if (isShooting) {
                            runShootingForwarAnim.setCurrentFrame(
                                    runForwardAnim.getCurrentFrame() - 1);
                            runShootingForwarAnim.draw(g2,
                                    (int) (getPosX() - getGameManager().camera
                                            .getPosX()),
                                    (int) getPosY()
                                            - (int) getGameManager().camera
                                                    .getPosY());
                        } else
                            runForwardAnim.draw(g2,
                                    (int) (getPosX() - getGameManager().camera
                                            .getPosX()),
                                    (int) getPosY()
                                            - (int) getGameManager().camera
                                                    .getPosY());
                        if (runForwardAnim.getCurrentFrame() == 1)
                            runForwardAnim.setIgnoreFrame(0);
                    } else if (getSpeedX() < 0) {
                        runBackAnim.update(System.nanoTime());
                        if (isShooting) {
                            runShootingBackAnim.setCurrentFrame(
                                    runBackAnim.getCurrentFrame() - 1);
                            runShootingBackAnim.draw(g2,
                                    (int) (getPosX() - getGameManager().camera
                                            .getPosX()),
                                    (int) getPosY()
                                            - (int) getGameManager().camera
                                                    .getPosY());
                        } else
                            runBackAnim.draw(g2,
                                    (int) (getPosX() - getGameManager().camera
                                            .getPosX()),
                                    (int) getPosY()
                                            - (int) getGameManager().camera
                                                    .getPosY());
                        if (runBackAnim.getCurrentFrame() == 1)
                            runBackAnim.setIgnoreFrame(0);
                    } else {
                        if (getDirection() == RIGHT_DIR) {
                            if (isShooting) {
                                idleShootingForwardAnim
                                        .update(System.nanoTime());
                                idleShootingForwardAnim.draw(g2,
                                        (int) (getPosX()
                                                - getGameManager().camera
                                                        .getPosX()),
                                        (int) getPosY()
                                                - (int) getGameManager().camera
                                                        .getPosY());
                            } else {
                                idleForwardAnim.update(System.nanoTime());
                                idleForwardAnim.draw(g2, (int) (getPosX()
                                        - getGameManager().camera.getPosX()),
                                        (int) getPosY()
                                                - (int) getGameManager().camera
                                                        .getPosY());
                            }
                        } else {
                            if (isShooting) {
                                idleShootingBackAnim.update(System.nanoTime());
                                idleShootingBackAnim.draw(g2, (int) (getPosX()
                                        - getGameManager().camera.getPosX()),
                                        (int) getPosY()
                                                - (int) getGameManager().camera
                                                        .getPosY());
                            } else {
                                idleBackAnim.update(System.nanoTime());
                                idleBackAnim.draw(g2, (int) (getPosX()
                                        - getGameManager().camera.getPosX()),
                                        (int) getPosY()
                                                - (int) getGameManager().camera
                                                        .getPosY());
                            }
                        }
                    }
                }
            }

            break;

        case BEHURT:
            if (getDirection() == RIGHT_DIR) {
                behurtForwardAnim.draw(g2,
                        (int) (getPosX() - getGameManager().camera.getPosX()),
                        (int) getPosY()
                                - (int) getGameManager().camera.getPosY());
            } else {
                behurtBackAnim
                        .setCurrentFrame(behurtForwardAnim.getCurrentFrame());
                behurtBackAnim.draw(g2,
                        (int) (getPosX() - getGameManager().camera.getPosX()),
                        (int) getPosY()
                                - (int) getGameManager().camera.getPosY());
            }
            break;

        case FEY:

            break;
        }

        drawBoundForCollisionWithMap(g2);
        // drawBoundForCollisionWithEnemy(g2);
    }

    @Override
    public void run() {
        if (getDirection() == LEFT_DIR)
            setSpeedX(-3);
        else
            setSpeedX(3);
    }

    @Override
    public void jump() {

        if (!getIsJumping()) {
            setIsJumping(true);
            setSpeedY(-5.0f);
            flyBackAnim.reset();
            flyForwardAnim.reset();
        }
        // for clim wall
        else {
            Rectangle rectRightWall = getBoundForCollisionWithMap();
            rectRightWall.x += 1;
            Rectangle rectLeftWall = getBoundForCollisionWithMap();
            rectLeftWall.x -= 1;

            if (getGameManager().physicalMap
                    .haveCollisionWithRightWall(rectRightWall) != null
                    && getSpeedX() > 0) {
                setSpeedY(-5.0f);
                // setSpeedX(-1);
                flyBackAnim.reset();
                flyForwardAnim.reset();
                // setDirection(LEFT_DIR);
            } else if (getGameManager().physicalMap
                    .haveCollisionWithLeftWall(rectLeftWall) != null
                    && getSpeedX() < 0) {
                setSpeedY(-5.0f);
                // setSpeedX(1);
                flyBackAnim.reset();
                flyForwardAnim.reset();
                // setDirection(RIGHT_DIR);
            }
        }
    }

    @Override
    public void dick() {
        if (!getIsJumping())
            setIsDicking(true);
    }

    @Override
    public void standUp() {
        setIsDicking(false);
        idleForwardAnim.reset();
        idleBackAnim.reset();
        dickForwardAnim.reset();
        dickBackAnim.reset();
    }

    @Override
    public void stopRun() {
        setSpeedX(0);
        runForwardAnim.reset();
        runBackAnim.reset();
        runForwardAnim.unIgnoreFrame(0);
        runBackAnim.unIgnoreFrame(0);
    }

    @Override
    public void attack() {

    }

    @Override
    public void hurtingCallback() {
        System.out.println("Call back hurting");

    }

}
