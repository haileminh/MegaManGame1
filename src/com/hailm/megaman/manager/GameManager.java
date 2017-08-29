package com.hailm.megaman.manager;

import java.awt.Graphics2D;

import com.hailm.megaman.model.Camera;
import com.hailm.megaman.model.MegaMan;
import com.hailm.megaman.model.PhysicalMap;
import com.hailm.megaman.view.Gui;

public class GameManager {

    public MegaMan megaman;

    public PhysicalMap physicalMap;

    public BulletManager bulletManager;

    public Camera camera;

    public GameManager() {
        megaman = new MegaMan(300, 300, this);
        physicalMap = new PhysicalMap(0, 0, this);
        camera = new Camera(0, 0, Gui.WIDTH_FRAME, Gui.HEIGHT_FRAME, this);
        bulletManager = new BulletManager(this);

    }

    public void update() {
        megaman.update();
        camera.update();
        bulletManager.updateObjects();

    }

    public void render(Graphics2D g2) {

        physicalMap.draw(g2);
        megaman.draw(g2);
        bulletManager.draw(g2);

    }

}
