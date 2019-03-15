package com.steevedoppler.projetopengles.GameObjects;

public class GameObjectImpl extends AnimatedGameObject {

    private boolean turn;

    public GameObjectImpl(float width, float height, float textPosX, float textPosY, int frames) {
        super(width, height, textPosX, textPosY, frames);
    }

    public void turn(boolean turn) {
        this.turn=turn;
    }

    @Override
    public void update(long dt) {
        super.update(dt);
        if (turn)
            angle+=dt*0.1f;
    }
}
