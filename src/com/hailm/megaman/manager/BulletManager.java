package com.hailm.megaman.manager;

import com.hailm.megaman.model.ParticularObject;

public class BulletManager extends ObjectManager {

    public BulletManager(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void updateObjects() {
        super.updateObjects();

        synchronized (listParticularObjects) {
            for (int i = 0; i < listParticularObjects.size(); i++) {
                ParticularObject object = listParticularObjects.get(i);
                if (object.isObjectOutOfCameraView()
                        || object.getState() == ParticularObject.DEATH) {
                    listParticularObjects.remove(i);
                }
            }
        }
    }
}
