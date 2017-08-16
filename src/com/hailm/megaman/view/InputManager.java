package com.hailm.megaman.view;

import java.awt.event.KeyEvent;

public class InputManager {
    public void processKeyPressed(int keyCode) {

        switch (keyCode) {
        case KeyEvent.VK_UP:
            System.out.println("You press UP");
            break;
        case KeyEvent.VK_DOWN:
            System.out.println("You press DOWN");
            break;
        case KeyEvent.VK_LEFT:
            System.out.println("You press LEFT");
            break;
        case KeyEvent.VK_RIGHT:
            System.out.println("You press RIGHT");
            break;
        case KeyEvent.VK_ENTER:

            break;
        case KeyEvent.VK_SPACE:

            break;
        case KeyEvent.VK_A:

            break;

        default:
            break;
        }
    }

    public void processKeyReleased(int keyCode) {

        switch (keyCode) {
        case KeyEvent.VK_UP:
            System.out.println("You Released UP");
            break;
        case KeyEvent.VK_DOWN:
            System.out.println("You Released DOWN");
            break;
        case KeyEvent.VK_LEFT:
            System.out.println("You Released LEFT");
            break;
        case KeyEvent.VK_RIGHT:
            System.out.println("You Released RIGHT");
            break;
        case KeyEvent.VK_ENTER:

            break;
        case KeyEvent.VK_SPACE:

            break;
        case KeyEvent.VK_A:

            break;

        default:
            break;
        }
    }
}