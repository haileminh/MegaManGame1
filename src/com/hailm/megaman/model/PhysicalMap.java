package com.hailm.megaman.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import com.hailm.megaman.manager.CacheDataLoader;
import com.hailm.megaman.manager.GameManager;

public class PhysicalMap extends GameObject {

    public int[][] phys_map;

    private int tileSize;

    public PhysicalMap(float posX, float posY, GameManager gameManager) {
        super(posX, posY, gameManager);
        this.tileSize = 30;
        phys_map = CacheDataLoader.getInstance().getPhysicalMap();
    }

    public int getTileSize() {
        return tileSize;
    }

    public void draw(Graphics2D g2) {

        g2.setColor(Color.GRAY);
        for (int i = 0; i < phys_map.length; i++)
            for (int j = 0; j < phys_map[0].length; j++)
                if (phys_map[i][j] != 0)
                    g2.fillRect((int) getPosX() + j * tileSize,
                            (int) getPosY() + i * tileSize, tileSize, tileSize);

    }

    public Rectangle haveCollisionWithLand(Rectangle rect) {

        int posX1 = rect.x / tileSize;
        posX1 -= 2;

        int posX2 = (rect.x + rect.width) / tileSize;
        posX2 += 2;

        int posY1 = (rect.y + rect.height) / tileSize;

        if (posX1 < 0)
            posX1 = 0;

        if (posX2 >= phys_map[0].length)
            posX2 = phys_map[0].length - 1;

        for (int y = posY1; y < phys_map.length; y++) {
            for (int x = posX1; x <= posX2; x++) {

                if (phys_map[y][x] == 1) {
                    Rectangle r = new Rectangle((int) getPosX() + x * tileSize,
                            (int) getPosY() + y * tileSize, tileSize, tileSize);
                    if (rect.intersects(r))
                        return r;
                }
            }
        }
        return null;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

    }

}
