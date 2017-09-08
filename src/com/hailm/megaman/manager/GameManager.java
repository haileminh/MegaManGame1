package com.hailm.megaman.manager;

import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics2D;

import com.hailm.megaman.model.BackgroundMap;
import com.hailm.megaman.model.Camera;
import com.hailm.megaman.model.FinalBoss;
import com.hailm.megaman.model.MegaMan;
import com.hailm.megaman.model.MonsterDevil;
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

    public AudioClip bgMusic;

    public GameManager() {
        megaman = new MegaMan(300, 300, this);
        megaman.setTeamType(ParticularObject.LEAGUE_TEAM);
        physicalMap = new PhysicalMap(0, 0, this);
        backgroundMap = new BackgroundMap(0, 0, this);
        camera = new Camera(0, 0, Gui.WIDTH_FRAME, Gui.HEIGHT_FRAME, this);

        bulletManager = new BulletManager(this);
        objectManager = new ObjectManager(this);
        objectManager.addObject(megaman);
        bgMusic = CacheDataLoader.getInstance().getSound("bgmusic");
        initsEnenies();

    }

    private void initsEnenies() {
        bgMusic.play();
        ParticularObject redeye = new RedEyeDevil(1250, 410, this);
        redeye.setDirection(ParticularObject.LEFT_DIR);
        redeye.setTeamType(ParticularObject.ENEMY_TEAM);
        objectManager.addObject(redeye);

        ParticularObject redeye2 = new RedEyeDevil(2500, 500, this);
        redeye2.setDirection(ParticularObject.LEFT_DIR);
        redeye2.setTeamType(ParticularObject.ENEMY_TEAM);
        objectManager.addObject(redeye2);

        ParticularObject redeye3 = new RedEyeDevil(3450, 500, this);
        redeye3.setDirection(ParticularObject.LEFT_DIR);
        redeye3.setTeamType(ParticularObject.ENEMY_TEAM);
        objectManager.addObject(redeye3);

        ParticularObject redeye4 = new RedEyeDevil(500, 1190, this);
        redeye4.setDirection(ParticularObject.RIGHT_DIR);
        redeye4.setTeamType(ParticularObject.ENEMY_TEAM);
        objectManager.addObject(redeye4);

        ParticularObject monster = new MonsterDevil(800, 800, this);
        monster.setDirection(ParticularObject.LEFT_DIR);
        monster.setTeamType(ParticularObject.ENEMY_TEAM);
        objectManager.addObject(monster);

        ParticularObject finalBoss = new FinalBoss(4300, 410, this);
        finalBoss.setDirection(ParticularObject.LEFT_DIR);
        finalBoss.setTeamType(ParticularObject.ENEMY_TEAM);
        objectManager.addObject(finalBoss);

    }

    public void update() {
        // megaman.update();
        //
        objectManager.updateObjects();
        camera.update();
        bulletManager.updateObjects();

        if (megaman.getState() == ParticularObject.DEATH) {
            bgMusic.stop();
        }

    }

    public void render(Graphics2D g2) {
        // physicalMap.draw(g2);
        // megaman.draw(g2);

        backgroundMap.draw(g2);
        objectManager.draw(g2);
        bulletManager.draw(g2);

        
        
        if (megaman.getState() == ParticularObject.DEATH) {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, Gui.WIDTH_FRAME, Gui.HEIGHT_FRAME);
            g2.setColor(Color.WHITE);
            g2.drawString("GAME OVER!", 450, 300);
        }
        if (megaman.getPosX() >= 4200) {
            g2.drawImage(CacheDataLoader.getInstance().getFrameImage("gamewin")
                    .getImage(), 300, 300, null);
        }

    }

}
