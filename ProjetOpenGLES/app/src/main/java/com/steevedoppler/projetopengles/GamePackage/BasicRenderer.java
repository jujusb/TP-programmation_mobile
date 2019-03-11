package com.steevedoppler.projetopengles.GamePackage;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.PointF;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.SparseArray;

import com.steevedoppler.projetopengles.GameObjects.AnimatedGameObject;
import com.steevedoppler.projetopengles.R;
import com.steevedoppler.projetopengles.common.RawResourceReader;
import com.steevedoppler.projetopengles.common.ShaderHelper;
import com.steevedoppler.projetopengles.common.TextureHelper;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static android.opengl.GLES20.GL_ONE;
import static android.opengl.GLES20.GL_ONE_MINUS_SRC_ALPHA;


public abstract class BasicRenderer implements GLSurfaceView.Renderer {

    protected final Context mActivityContext; // store the context where the view is created

    // Time variables
    protected long startTime;
    protected long deltaTime;

    // Matrices used for transformation
    protected float[] mModelMatrix = new float[16];
    protected float[] mViewMatrix = new float[16];
    protected float[] mOrthoMatrix = new float[16];
    protected float[] mProjectionMatrix = new float[16];
    private float[] mMVPMatrix = new float[16];

    // Handle for the shaders
    private int mMVPMatrixHandle;
    private int mMVMatrixHandle;
    private int mTextureUniformHandle;
    private int mPositionHandle;

    private int mTextureCoordinateHandle;
    protected int mTextureDataHandle;

    private int mColorUniformHandle;

    private final int mPositionDataSize = 2;
    private final int mTextureCoordinateDataSize = 2;

    // Handle for the shader programs
    protected int mProgramTextureHandle;


    // Game variables
    protected float screenRatio;

    protected SharedPreferences settings;
    protected SharedPreferences.Editor editor;


    public BasicRenderer(Context mActivityContext) {
        this.mActivityContext = mActivityContext;

        settings = mActivityContext.getSharedPreferences("com.steevedoppler.halfcircle", Context.MODE_PRIVATE);
        editor = settings.edit();
        editor.commit();
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        /* GL INITIALISATION */
        GLES20.glClearColor(1,1,1,1);

        //GLES20.glEnable(GLES20.GL_CULL_FACE);
        GLES20.glDisable(GLES20.GL_DEPTH_TEST);
        GLES20.glEnable(GLES20.GL_BLEND);
        GLES20.glBlendFunc(GL_ONE,GL_ONE_MINUS_SRC_ALPHA);

        /* TEXTURE SHADER PROGRAM */
        final String textureVertexShader = getTextureVertexShader();
        final String textureFragmentShader = getTextureFragmentShader();

        final int textureVertexShaderHandle = ShaderHelper.compileShader(GLES20.GL_VERTEX_SHADER, textureVertexShader);
        final int textureFragmentShaderHandle = ShaderHelper.compileShader(GLES20.GL_FRAGMENT_SHADER, textureFragmentShader);

        mProgramTextureHandle = ShaderHelper.createAndLinkProgram(textureVertexShaderHandle, textureFragmentShaderHandle,
                new String[]{ "a_Position", "a_TexCoordinate" });

        mTextureDataHandle = TextureHelper.loadTexture(mActivityContext, R.drawable.tileset);



        final float eyeX = 0.0f;
        final float eyeY = 0.0f;
        final float eyeZ = 2.5f;

        // We are looking toward the distance
        final float lookX = 0.0f;
        final float lookY = 0.0f;
        final float lookZ = -5.0f;

        // Set our up vector. This is where our head would be pointing were we holding the camera.
        final float upX = 0.0f;
        final float upY = 1.0f;
        final float upZ = 0.0f;

        // Set the view matrix. This matrix can be said to represent the camera position.
        // NOTE: In OpenGL 1, a ModelView matrix is used, which is a combination of a model and
        // view matrix. In OpenGL 2, we can keep track of these matrices separately if we choose.
        Matrix.setLookAtM(mViewMatrix, 0, eyeX, eyeY, eyeZ, lookX, lookY, lookZ, upX, upY, upZ);



        startTime = System.currentTimeMillis();

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        GLES20.glViewport(0,0,width, height);

        final float ratio = (float) height / width;
        screenRatio = ratio;
        final float left = -1.0f;
        final float right = 1.0f;
        final float bottom = -ratio;
        final float top = ratio;
        final float near = 0.1f;
        final float far = 10.0f;


        final float size = 10f;
        Matrix.orthoM(mOrthoMatrix, 0, left*size, right*size, bottom*size, top*size, near, far);
        //Matrix.frustumM(mProjectionMatrix, 0, left, right, bottom, top, near, far);
    }


    @Override
    public void onDrawFrame(GL10 gl10) {
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);
        computeTime();
    }

    /**
     * Computes the time between each frames
     */
    private void computeTime() {
        deltaTime = System.currentTimeMillis() - startTime;
        startTime = System.currentTimeMillis();
    }


    protected String getTextureVertexShader() {
        return RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.vertex_shader);
    }
    protected String getTextureFragmentShader() {
        return RawResourceReader.readTextFileFromRawResource(mActivityContext, R.raw.fragment_shader);
    }

    protected void prepareTextureShader() {
        GLES20.glUseProgram(mProgramTextureHandle);


        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgramTextureHandle, "u_MVPMatrix");
        mMVMatrixHandle = GLES20.glGetUniformLocation(mProgramTextureHandle, "u_MVMatrix");
        mTextureUniformHandle = GLES20.glGetUniformLocation(mProgramTextureHandle, "u_Texture");
        mColorUniformHandle = GLES20.glGetUniformLocation(mProgramTextureHandle, "u_Color");
        mPositionHandle = GLES20.glGetAttribLocation(mProgramTextureHandle, "a_Position");
        mTextureCoordinateHandle = GLES20.glGetAttribLocation(mProgramTextureHandle, "a_TexCoordinate");


        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mTextureDataHandle);
        GLES20.glUniform1i(mTextureUniformHandle, 0);
    }



    protected void drawObject(AnimatedGameObject obj, float[] color) {
        obj.getCubePosition().position(0);
        GLES20.glVertexAttribPointer(mPositionHandle, mPositionDataSize, GLES20.GL_FLOAT, false,
                0, obj.getCubePosition());
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        obj.getCubeTextureCoordinate().position(0);
        GLES20.glVertexAttribPointer(mTextureCoordinateHandle, mTextureCoordinateDataSize, GLES20.GL_FLOAT, false,
                0, obj.getCubeTextureCoordinate());
        GLES20.glEnableVertexAttribArray(mTextureCoordinateHandle);

        Matrix.setIdentityM(mModelMatrix, 0);
        Matrix.translateM(mModelMatrix, 0, obj.getX(), obj.getY(), obj.getZ());
        Matrix.rotateM(mModelMatrix, 0, obj.getAngle(), 0,0,1);

        Matrix.multiplyMM(mMVPMatrix, 0, mViewMatrix, 0, mModelMatrix, 0);
        GLES20.glUniformMatrix4fv(mMVMatrixHandle, 1, false, mMVPMatrix, 0);

        Matrix.multiplyMM(mMVPMatrix, 0, mOrthoMatrix, 0, mMVPMatrix, 0);
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, mMVPMatrix, 0);

        GLES20.glUniform4fv(mColorUniformHandle, 1, color,0);

        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, 6);
    }


    public void passActivePointers(SparseArray<PointF> mActivePointers, boolean actionMove) {}

}
