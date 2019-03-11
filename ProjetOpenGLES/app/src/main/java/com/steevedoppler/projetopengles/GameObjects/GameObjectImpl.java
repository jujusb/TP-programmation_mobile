package com.steevedoppler.projetopengles.GameObjects;

public class GameObjectImpl extends AnimatedGameObject {
    public GameObjectImpl(float width, float height, float textPosX, float textPosY, int frames) {
        super(width, height, textPosX, textPosY, frames);
    }

    @Override
    public void update(long dt) {
        super.update(dt);

        angle+=dt*0.1f;
    }
}
