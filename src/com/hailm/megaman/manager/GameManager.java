package com.hailm.megaman.manager;

import java.awt.Graphics2D;

import com.hailm.megaman.model.BackgroundMap;
import com.hailm.megaman.model.Camera;
import com.hailm.megaman.model.MegaMan;
import com.hailm.megaman.model.ParticularObject;
import com.hailm.megaman.model.PhysicalMap;
import com.hailm.megaman.model.RedEyeDevil;
import com.hailm.megaman.view.Gui;

public class GameManager {

    public MegaMan megaman;

    public PhysicalMap physicalMap;

    public BackgroundMap backgroundMap;

    public BulletManager bulletManager;

    public ObjectManager objectManager;

    public Camera camera;

    public GameManager() {
        megaman = new MegaMan(300, 300, this);
        megaman.setTeamType(ParticularObject.LEAGUE_TEAM);
        physicalMap = new PhysicalMap(0, 0, this);
        backgroundMap = new BackgroundMap(0, 0, this);
        camera = new Camera(0, 0, Gui.WIDTH_FRAME, Gui.HEIGHT_FRAME, this);

        bulletManager = new BulletManager(this);
        objectManager = new ObjectManager(this);
        objectManager.addObject(megaman);

        initsEnenies();
    }

    private void initsEnenies() {
        ParticularObject redeye = new RedEyeDevil(1250, 410, this);
        redeye.setDirection(ParticularObject.LEFT_DIR);
        redeye.setTeamType(ParticularObject.ENEMY_TEAM);
        objectManager.addObject(redeye);
    }

    public void update() {
        // megaman.update();

        objectManager.updateObjects();
        camera.update();
        bulletManager.updateObjects();
    }

    public void render(Graphics2D g2) {

        // physicalMap.draw(g2);
        backgroundMap.draw(g2);
        // megaman.draw(g2);
        objectManager.draw(g2);
        bulletManager.draw(g2);

    }

}
