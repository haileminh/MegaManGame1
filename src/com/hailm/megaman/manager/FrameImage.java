package com.hailm.megaman.manager;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class FrameImage {
    private String name;

    private BufferedImage image;

    public FrameImage(String name, BufferedImage image) {
        this.name = name;
        this.image = image;
    }

    public FrameImage(FrameImage frameImage) {
        image = new BufferedImage(frameImage.getImageWidth(),
                frameImage.getImageHeight(), frameImage.image.getType());
        Graphics g = image.getGraphics();
        g.drawImage(frameImage.getImage(), 0, 0, null);
        // name = frameImage.name;
    }

    public void draw(Graphics2D graphics2d, int x, int y) {
        graphics2d.drawImage(image, x - getImageWidth(),
                y - getImageHeight() / 2, null);
    }

    public int getImageWidth() {
        return image.getWidth();
    }

    public int getImageHeight() {
        return image.getHeight();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }

}