package com.hailm.megaman.model;

import com.hailm.megaman.manager.GameManager;

public class Camera extends GameObject {
    private float widthView;

    private float heigthView;

    private boolean isLocked = false;

    public Camera(float posX, float posY, float widthView, float heigthView,
            GameManager gameManager) {
        super(posX, posY, gameManager);
        this.widthView = widthView;
        this.heigthView = heigthView;
    }

    public void lock() {
        isLocked = true;
    }

    public void unLock() {
        isLocked = false;
    }

    @Override
    public void update() {
        if (!isLocked) {

            MegaMan man = getGameManager().megaman;

            if (man.getPosX() - getPosX() > 400) {
                setPosX(man.getPosX() - 400);
            }

            if (man.getPosX() - getPosX() < 200) {
                setPosX(man.getPosX() - 200);
            }

            if (man.getPosY() - getPosY() > 400) {
                setPosY(man.getPosY() - 400);
            } else if (man.getPosY() - getPosY() < 250) {
                setPosY(man.getPosY() - 250);
            }
        }
    }

    public float getWidthView() {
        return widthView;
    }

    public void setWidthView(float widthView) {
        this.widthView = widthView;
    }

    public float getHeigthView() {
        return heigthView;
    }

    public void setHeigthView(float heigthView) {
        this.heigthView = heigthView;
    }

}
