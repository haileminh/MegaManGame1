package com.hailm.megaman.model;

import java.awt.Graphics2D;

import com.hailm.megaman.manager.CacheDataLoader;
import com.hailm.megaman.manager.GameManager;
import com.hailm.megaman.view.Gui;

public class BackgroundMap extends GameObject {

    public int[][] map;

    private int tileSize;

    public BackgroundMap(float posX, float posY, GameManager gameManager) {
        super(posX, posY, gameManager);
        map = CacheDataLoader.getInstance().getBackgroundMap();
        tileSize = 30;
    }

    @Override
    public void update() {
    }

    public void draw(Graphics2D g2) {
        Camera camera = getGameManager().camera;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j] != 0 && j * tileSize - camera.getPosX() > -30
                        && j * tileSize - camera.getPosX() < Gui.WIDTH_FRAME
                        && i * tileSize - camera.getPosY() > -30
                        && i * tileSize - camera.getPosY() < Gui.HEIGHT_FRAME) {
                    g2.drawImage(CacheDataLoader.getInstance()
                            .getFrameImage("tiled" + map[i][j]).getImage(),
                            (int) getPosX() + j * tileSize
                                    - (int) camera.getPosX(),
                            (int) getPosY() + i * tileSize
                                    - (int) camera.getPosY(),
                            null);
                }
            }
        }
    }
}
