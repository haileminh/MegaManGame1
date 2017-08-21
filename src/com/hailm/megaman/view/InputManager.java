package com.hailm.megaman.view;

import java.awt.event.KeyEvent;

import com.hailm.megaman.manager.GameManager;
import com.hailm.megaman.model.MegaMan;

public class InputManager {

    private GameManager gameManager;

    public InputManager(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public void processKeyPressed(int keyCode) {

        switch (keyCode) {

        case KeyEvent.VK_UP:
            break;

        case KeyEvent.VK_DOWN:
            break;

        case KeyEvent.VK_LEFT:
            gameManager.megaman.setDirection(MegaMan.DIR_LEFT);
            gameManager.megaman.setSpeedX(-1);

            gameManager.physicalMap.posX -= 3;
            break;
        case KeyEvent.VK_RIGHT:
            gameManager.megaman.setDirection(MegaMan.DIR_RIGHT);
            gameManager.megaman.setSpeedX(1);
            gameManager.physicalMap.posX += 3;
            break;

        case KeyEvent.VK_ENTER:

            break;

        case KeyEvent.VK_SPACE:
            gameManager.megaman.setSpeedY(-3);
            gameManager.megaman.setPosY(gameManager.megaman.getPosY() - 3);
            break;
        case KeyEvent.VK_A:

            break;

        }

    }

    public void processKeyReleased(int keyCode) {

        switch (keyCode) {

        case KeyEvent.VK_UP:
            System.out.println("You released UP");
            break;

        case KeyEvent.VK_DOWN:
            System.out.println("You released DOWN");
            break;

        case KeyEvent.VK_LEFT:
            gameManager.megaman.setSpeedX(0);
            break;
        case KeyEvent.VK_RIGHT:
            gameManager.megaman.setSpeedX(0);
            break;

        case KeyEvent.VK_ENTER:

            break;

        case KeyEvent.VK_SPACE:

            break;
        case KeyEvent.VK_A:

            break;

        }

    }

}