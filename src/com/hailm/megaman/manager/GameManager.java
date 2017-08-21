package com.hailm.megaman.manager;

import java.awt.Graphics2D;

import com.hailm.megaman.model.MegaMan;
import com.hailm.megaman.model.PhysicalMap;

public class GameManager {

    public MegaMan megaman;

    public PhysicalMap physicalMap;

    public GameManager() {
        megaman = new MegaMan(300, 300, 100, 100, 0.1f, this);
        physicalMap = new PhysicalMap(0, 0, this);
    }

    public void update() {
        megaman.update();
    }

    public void render(Graphics2D g2) {

        physicalMap.draw(g2);
        megaman.draw(g2);

    }

}
