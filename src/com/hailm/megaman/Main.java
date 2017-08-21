package com.hailm.megaman;

import com.hailm.megaman.view.Gui;

public class Main {
    public static void main(String[] args) {
        Gui gui = new Gui();
        gui.setVisible(true);
        gui.startGame();
    }
}
