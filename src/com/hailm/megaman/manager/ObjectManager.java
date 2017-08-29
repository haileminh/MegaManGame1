package com.hailm.megaman.manager;

import java.awt.Graphics2D;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.hailm.megaman.model.ParticularObject;

public class ObjectManager {
    protected List<ParticularObject> listParticularObjects;

    private GameManager gameManager;

    public ObjectManager(GameManager gameManager) {
        this.gameManager = gameManager;
        listParticularObjects = Collections
                .synchronizedList(new LinkedList<ParticularObject>());

    }

    public GameManager getGameManager() {
        return gameManager;
    }

    public void addObject(ParticularObject particularObject) {
        synchronized (listParticularObjects) {
            listParticularObjects.add(particularObject);
        }
    }

    public void removeObject(ParticularObject particularObject) {
        synchronized (listParticularObjects) {
            for (int i = 0; i < listParticularObjects.size(); i++) {
                ParticularObject object = listParticularObjects.get(i);

                if (object == particularObject) {
                    listParticularObjects.remove(i);
                }
            }
        }
    }

    public ParticularObject getCollisionWidthEnemyObject(
            ParticularObject object) {
        synchronized (listParticularObjects) {
            for (int i = 0; i < listParticularObjects.size(); i++) {

                ParticularObject objectInList = listParticularObjects.get(i);

                if (object.getTeamType() != objectInList.getTeamType()
                        && object.getBoundForCollisionWithEnemy().intersects(
                                objectInList.getBoundForCollisionWithEnemy())) {
                    return objectInList;
                }
            }
        }
        return null;
    }

    public void updateObjects() {
        synchronized (listParticularObjects) {
            for (int i = 0; i < listParticularObjects.size(); i++) {
                ParticularObject object = listParticularObjects.get(i);

                if (!object.isObjectOutOfCameraView()) {
                    object.update();
                }

                if (object.getState() == ParticularObject.DEATH) {
                    listParticularObjects.remove(i);
                }
            }
        }
    }

    public void draw(Graphics2D g2d) {
        synchronized (listParticularObjects) {
            for (ParticularObject object : listParticularObjects) {
                if (!object.isObjectOutOfCameraView()) {
                    object.draw(g2d);
                }
            }
        }
    }
}
