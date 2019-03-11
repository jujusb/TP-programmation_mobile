package com.steevedoppler.projetopengles.GameObjects;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public abstract class AnimatedGameObject {

    private FloatBuffer mCubePosition;
    private FloatBuffer[] mCubeTextureCoordinate;

    private final int mBytesPerFloat = 4;


    private float texturePositionX;
    private float texturePositionY;


    // Size of the tileset
    private final static int textureWidth = 8;
    private final static int textureHeight = 4;

    protected float x, y, z;
    protected float angle;
    private float width, height;
    protected int currentFrame=0;

    public AnimatedGameObject(float width, float height, float textPosX, float textPosY, int frames) {

        this.width = width;
        this.height = height;
        this.texturePositionX=textPosX;
        this.texturePositionY=textPosY;

        final float[] cubePositionData =
                {
                        // Front face
                        -width/2, height/2,
                        -width/2, -height/2,
                        width/2, height/2,
                        -width/2, -height/2,
                        width/2, -height/2,
                        width/2, height/2
                };
        // we build different tex coords for each frame of the animation
        float[][] cubeTextureCoordinateData = new float[12][];
        for (int i=0 ; i<frames ; i++) {

            // Added 0.001f to prevent buttons to touch another tile in tileset (that damn pause button)
            cubeTextureCoordinateData[i] =
                    new float[]{

                            // Front face
                            (texturePositionX+0.001f) / textureWidth, (texturePositionY+i) / textureHeight,
                            (texturePositionX+0.001f) / textureWidth, ((texturePositionY+i) + 1) / textureHeight,
                            ((texturePositionX) + 1) / textureWidth, (texturePositionY+i) / textureHeight,
                            (texturePositionX+0.001f) / textureWidth, ((texturePositionY+i) + 1) / textureHeight,
                            ((texturePositionX) + 1) / textureWidth, ((texturePositionY+i) + 1) / textureHeight,
                            ((texturePositionX) + 1) / textureWidth, (texturePositionY+i) / textureHeight
                    };
        }

        mCubePosition = ByteBuffer.allocateDirect(cubePositionData.length * mBytesPerFloat)
                .order(ByteOrder.nativeOrder()).asFloatBuffer();
        mCubePosition.put(cubePositionData).position(0);

        mCubeTextureCoordinate = new FloatBuffer[frames];
        for (int i=0 ; i<frames ; i++) {
            mCubeTextureCoordinate[i] = ByteBuffer.allocateDirect(cubeTextureCoordinateData.length * mBytesPerFloat)
                    .order(ByteOrder.nativeOrder()).asFloatBuffer();
            mCubeTextureCoordinate[i].put(cubeTextureCoordinateData[i]).position(0);
        }

    }

    public void update(long dt) {}

    public FloatBuffer getCubePosition() {
        return mCubePosition;
    }

    public FloatBuffer getCubeTextureCoordinate() {
        return mCubeTextureCoordinate[currentFrame];
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getAngle() {
        return angle;
    }

    public void setAngle(float angle) {
        this.angle=angle;
    }

    public void setCurrentFrame(int i) {
        currentFrame=i;
    }
}
