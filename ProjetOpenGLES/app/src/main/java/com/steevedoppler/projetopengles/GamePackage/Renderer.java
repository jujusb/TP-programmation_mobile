package com.steevedoppler.projetopengles.GamePackage;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;
import android.util.SparseArray;

import com.steevedoppler.projetopengles.GameObjects.GameObjectImpl;

import javax.microedition.khronos.opengles.GL10;


public class Renderer extends BasicRenderer {


    // Objects
    GameObjectImpl gameObject;
    float angle;

    public Renderer(final Context mActivityContext) {
        super(mActivityContext);

        gameObject = new GameObjectImpl(5,5,0,0,1);
        gameObject.setX(0);
        gameObject.setY(0);

        angle = 0;
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);
    }

    // Method called every frame
    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);

        gameObject.update(deltaTime);

        // Drawing
        prepareTextureShader();
        drawObject(gameObject, new float[]{1,1,1,1});

    }


    @Override
    public void passActivePointers(SparseArray<PointF> mActivePointers, boolean actionMove) {
        super.passActivePointers(mActivePointers, actionMove);

        int size = mActivePointers.size();
        for (int j = 0 ; j < size ; j++) {

            int i = mActivePointers.keyAt(j);

            if (mActivePointers.get(i) != null) {

            }
        }

    }


}
