package com.steevedoppler.projetopengles.GamePackage;

import android.content.Context;
import android.graphics.PointF;
import android.util.Log;
import android.util.SparseArray;

import com.steevedoppler.projetopengles.GameObjects.GameObjectImpl;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;


public class Renderer extends BasicRenderer {


    // Objects
    ArrayList<GameObjectImpl> objects;
    GameObjectImpl gameObject;

    public Renderer(final Context mActivityContext) {
        super(mActivityContext);

        objects = new ArrayList<>();

        gameObject = new GameObjectImpl(5,5,0,0,1);
        gameObject.setX(0);
        gameObject.setY(0);

        objects.add(gameObject);


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        super.onSurfaceChanged(gl, width, height);
    }

    // Method called every frame
    @Override
    public void onDrawFrame(GL10 gl) {
        super.onDrawFrame(gl);

        try {
            for (GameObjectImpl o : objects)
                o.update(deltaTime);
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }

        // Drawing
        prepareTextureShader();
        try {
            for (GameObjectImpl o : objects)
                drawObject(o, new float[]{1, 1, 1, 1});
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }
    }


    private boolean sizeReset = true;

    @Override
    public void passActivePointers(SparseArray<PointF> mActivePointers, boolean actionMove) {
        super.passActivePointers(mActivePointers, actionMove);

        int size = mActivePointers.size();

        Random rand = new Random();

        if (size == 2 && sizeReset) {
            objects.add(new GameObjectImpl(5,5, rand.nextInt(8), rand.nextInt(3), 1));
            sizeReset = false;
        }
        else if (size == 0) {
            sizeReset = true;
        }

        for (GameObjectImpl o : objects)
            o.turn(false);


        for (int j = 0; j < size; j++) {

            int i = mActivePointers.keyAt(j);

            if (mActivePointers.get(i) != null) {

                for (GameObjectImpl o : objects) {

                    float xPoint = mActivePointers.get(i).x;
                    float yPoint = mActivePointers.get(i).y;

                    if (distance(xPoint,yPoint, o.getX(), o.getY()) < 6.25f*2f) {

                        o.setX(xPoint);
                        o.setY(yPoint);


                        if (actionMove) {
                            o.turn(true);
                        }

                        if (yPoint < -0.9*screenRatio*10) {
                            objects.remove(o);
                        }

                        break;

                    }
                }

            }
        }


    }

    public float distance(float xa, float ya, float xb, float yb) {
        return (xa-xb)*(xa-xb) + (ya-yb)*(ya-yb);
    }


}
