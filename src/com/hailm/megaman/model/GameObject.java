package com.hailm.megaman.model;

import com.hailm.megaman.manager.GameManager;

public abstract class GameObject {
    private float posX;

    private float posY;

    private GameManager gameManager;

    public GameObject(float posX, float posY, GameManager gameManager) {
        this.posX = posX;
        this.posY = posY;
        this.gameManager = gameManager;
    }

    public abstract void update();

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void setGameManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

}
