package com.steevedoppler.projetopengles;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.ConfigurationInfo;
import android.graphics.PointF;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.MotionEvent;

import com.steevedoppler.projetopengles.GamePackage.BasicRenderer;
import com.steevedoppler.projetopengles.GamePackage.Renderer;

public class MainActivity extends AppCompatActivity {

    private GLSurfaceView mGLSurfaceView;
    private BasicRenderer mRenderer;

    private SparseArray<PointF> mActivePointers; // stores the locations of the clicks
    private boolean actionMove = false;

    private float height, width, ratio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Force portrait mode
        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mGLSurfaceView = new GLSurfaceView(this);

        DisplayMetrics displaymetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
            getWindowManager().getDefaultDisplay().getRealMetrics(displaymetrics);
        else
            getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);

        height = displaymetrics.heightPixels;
        width = displaymetrics.widthPixels;
        ratio = height/width;

        final boolean supportES2 = configurationInfo.reqGlEsVersion >= 0x20000;

        if(supportES2) {
            mGLSurfaceView.setEGLContextClientVersion(2);
            mRenderer = new Renderer(this);
            mGLSurfaceView.setRenderer(mRenderer);
        }
        else {
            Log.e("Not supported", "onCreate: OpenGL ES 2.0 is not supported on this device");
            return;
        }

        mActivePointers = new SparseArray<>();

        setContentView(mGLSurfaceView);

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        // get pointer index from the event object
        int pointerIndex = event.getActionIndex();
        // get pointer ID
        int pointerId = event.getPointerId(pointerIndex);
        // get masked (not specific to a pointer) action
        int maskedAction = event.getActionMasked();

        actionMove = false;

        switch (maskedAction) {

            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN: {
                // We have a new pointer. Lets add it to the list of pointers
                PointF f = new PointF();
                f.x = event.getX(pointerIndex)*20/width-10;
                f.y = -event.getY(pointerIndex)*20*ratio/height+10*ratio;
                mActivePointers.put(pointerId, f);
                actionMove = false;
                break;
            }
            case MotionEvent.ACTION_MOVE: { // a pointer was moved

                for (int size = event.getPointerCount(), i = 0; i < size; i++) {
                    PointF point = mActivePointers.get(event.getPointerId(i));

                    if (point != null) {

                        point.x = event.getX(i)*20/width-10;
                        point.y = -event.getY(i)*20*ratio/height+10*ratio;
                        Log.d("Point", "onTouchEvent: at ("+point.x+";"+point.y+")");
                    }

                }

                actionMove = true;
                break;
            }
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL: {
                mActivePointers.remove(pointerId);
                break;
            }
        }
        mRenderer.passActivePointers(mActivePointers, actionMove);


        return super.onTouchEvent(event);
    }

}
