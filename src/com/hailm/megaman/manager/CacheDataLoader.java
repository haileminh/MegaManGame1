package com.hailm.megaman.manager;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Hashtable;

import javax.imageio.ImageIO;

public class CacheDataLoader {
    private static CacheDataLoader instance;

    private String frameFile = "src/res/data/frame.txt";

    private String animationFile = "src/res/data/animation.txt";

    private String physmapFile = "src/res/data/phys_map.txt";

    private String backgroundmapFile = "src/res/data/background_map.txt";

    private String soundFile = "src/res/data/sounds.txt";

    private Hashtable<String, FrameImage> frameImages;

    private Hashtable<String, Animation> animations;

    private Hashtable<String, AudioClip> sounds;

    private int[][] phys_map;

    private int[][] background_map;

    private CacheDataLoader() {
    }

    public static CacheDataLoader getInstance() {

        if (instance == null)
            instance = new CacheDataLoader();
        return instance;

    }

    public void loadData() throws IOException {

        loadFrame();
        loadAnimation();
        loadPhysicalMap();
        loadBackgroundMap();
        loadSounds();
    }

    public int[][] getPhysicalMap() {
        return instance.phys_map;
    }

    public int[][] getBackgroundMap() {
        return instance.background_map;
    }

    public AudioClip getSound(String name) {
        return instance.sounds.get(name);
    }

    public void loadSounds() throws IOException {
        sounds = new Hashtable<String, AudioClip>();
        FileReader fr = new FileReader(soundFile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        if (br.readLine() == null) {
            System.out.println("No data");
        } else {
            fr = new FileReader(soundFile);
            br = new BufferedReader(fr);

            while ((line = br.readLine()).equals(""))
                ;

            int n = Integer.parseInt(line);
            for (int i = 0; i < n; i++) {
                AudioClip audioClip = null;
                while ((line = br.readLine()).equals(""))
                    ;

                String[] str = line.split(" ");
                String name = str[0];

                try {
                    audioClip = Applet
                            .newAudioClip(new URL("file", "", str[1]));
                } catch (MalformedURLException e) {
                }

                instance.sounds.put(name, audioClip);
            }
        }
        br.close();
    }

    public void loadFrame() throws IOException {

        frameImages = new Hashtable<String, FrameImage>();

        FileReader fr = new FileReader(frameFile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        if (br.readLine() == null) {
            System.out.println("No data");
            throw new IOException();
        } else {

            fr = new FileReader(frameFile);
            br = new BufferedReader(fr);

            while ((line = br.readLine()).equals(""))
                ;

            int n = Integer.parseInt(line);

            for (int i = 0; i < n; i++) {

                FrameImage frame = new FrameImage();
                while ((line = br.readLine()).equals(""))
                    ;
                frame.setName(line);

                while ((line = br.readLine()).equals(""))
                    ;
                String[] str = line.split(" ");
                String path = str[1];

                while ((line = br.readLine()).equals(""))
                    ;
                str = line.split(" ");
                int x = Integer.parseInt(str[1]);

                while ((line = br.readLine()).equals(""))
                    ;
                str = line.split(" ");
                int y = Integer.parseInt(str[1]);

                while ((line = br.readLine()).equals(""))
                    ;
                str = line.split(" ");
                int w = Integer.parseInt(str[1]);

                while ((line = br.readLine()).equals(""))
                    ;
                str = line.split(" ");
                int h = Integer.parseInt(str[1]);

                BufferedImage imageData = ImageIO.read(new File(path));
                BufferedImage image = imageData.getSubimage(x, y, w, h);
                frame.setImage(image);

                instance.frameImages.put(frame.getName(), frame);
            }

        }

        br.close();

    }

    public FrameImage getFrameImage(String name) {

        FrameImage frameImage = new FrameImage(instance.frameImages.get(name));
        return frameImage;

    }

    public void loadAnimation() throws IOException {

        animations = new Hashtable<String, Animation>();

        FileReader fr = new FileReader(animationFile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        if (br.readLine() == null) {
            System.out.println("No data");
            throw new IOException();
        } else {

            fr = new FileReader(animationFile);
            br = new BufferedReader(fr);

            while ((line = br.readLine()).equals(""))
                ;
            int n = Integer.parseInt(line);

            for (int i = 0; i < n; i++) {

                Animation animation = new Animation();

                while ((line = br.readLine()).equals(""))
                    ;
                animation.setName(line);

                while ((line = br.readLine()).equals(""))
                    ;
                String[] str = line.split(" ");

                for (int j = 0; j < str.length; j += 2)
                    animation.add(getFrameImage(str[j]),
                            Double.parseDouble(str[j + 1]));

                instance.animations.put(animation.getName(), animation);

            }

        }

        br.close();
    }

    public Animation getAnimation(String name) {

        Animation animation = new Animation(instance.animations.get(name));
        return animation;

    }

    public void loadPhysicalMap() throws IOException {

        FileReader fr = new FileReader(physmapFile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);

        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);

        instance.phys_map = new int[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            line = br.readLine();
            String[] str = line.split(" ");
            for (int j = 0; j < numberOfColumns; j++) {
                instance.phys_map[i][j] = Integer.parseInt(str[j]);
            }
        }

        for (int i = 0; i < numberOfRows; i++) {
            for (int j = 0; j < numberOfColumns; j++) {
                System.out.print(" " + instance.phys_map[i][j]);

            }
            System.out.println();
        }

        br.close();
    }

    public void loadBackgroundMap() throws IOException {

        FileReader fr = new FileReader(backgroundmapFile);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        line = br.readLine();
        int numberOfRows = Integer.parseInt(line);
        line = br.readLine();
        int numberOfColumns = Integer.parseInt(line);

        instance.background_map = new int[numberOfRows][numberOfColumns];

        for (int i = 0; i < numberOfRows; i++) {
            line = br.readLine();
            String[] str = line.split(" |  ");
            for (int j = 0; j < numberOfColumns; j++)
                instance.background_map[i][j] = Integer.parseInt(str[j]);
        }

        for (int i = 0; i < numberOfRows; i++) {

            for (int j = 0; j < numberOfColumns; j++)
                System.out.print(" " + instance.background_map[i][j]);

            System.out.println();
        }

        br.close();

    }
}