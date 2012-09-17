package com.fARmework.modules.SpaceGraphics;

import java.nio.*;

import javax.microedition.khronos.egl.*;
import javax.microedition.khronos.opengles.*;

import android.opengl.*;

public class GraphicsRenderer implements GLSurfaceView.Renderer 
{
    private Triangle mTriangle;

    @Override
    public void onSurfaceCreated(GL10 unused, EGLConfig config) {

        // Set the background frame color
        GLES20.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

        mTriangle = new Triangle();
    }

    @Override
    public void onDrawFrame(GL10 unused) {

        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        // Draw triangle
        mTriangle.draw();
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        // Adjust the viewport based on geometry changes,
        // such as screen rotation
        GLES20.glViewport(0, 0, width, height);
    }
}

class Triangle 
{
	private final GLHandler _glHandler = new GLHandler();

    private final FloatBuffer vertexBuffer;
    private final ByteBuffer indexBuffer;

    // number of coordinates per vertex in this array
    static final int COORDS_PER_VERTEX = 3;
    static float vertices[] = {
		-0.5f,	 0.1f,	 0.0f,
		-0.1f,	 0.1f,	 0.25f,
		-0.1f,	 0.1f,	 0.15f,
		 0.5f,	 0.1f,	 0.15f,
		 0.5f,	 0.1f,	-0.15f,
		-0.1f,	 0.1f,	-0.15f,
		-0.1f,	 0.1f,	-0.25f,
		-0.5f,	-0.1f,	 0.0f,
		-0.1f,	-0.1f,	 0.25f,
		-0.1f,	-0.1f,	 0.15f,
		 0.5f,	-0.1f,	 0.15f,
		 0.5f,	-0.1f,	-0.15f,
		-0.1f,	-0.1f,	-0.15f,
		-0.1f,	-0.1f,	-0.25f
    };
    static byte indices[] = {
		0,	1,	6,
		2,	3,	5,
		3,	4,	5,
		7,	13,	8,
		9,	12,	10,
		12,	11,	10,
		0,	7,	8,
		0,	8,	1,
		1,	8,	9,
		1,	9,	2,
		2,	9,	10,
		2,	10,	3,
		3,	10,	11,
		3,	11,	4,
		4,	11,	12,
		4,	12,	5,
		5,	12,	13,
		5,	13,	6,
		6,	13,	7,
		6,	7,	0    	
    };
    private final int vertexStride = COORDS_PER_VERTEX * 4; // bytes per vertex

    // Set color with red, green, blue and alpha (opacity) values
    float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };

    public Triangle() {
        // initialize vertex byte buffer for shape coordinates
        ByteBuffer bb = ByteBuffer.allocateDirect(vertices.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer
        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(vertices);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);
        
        indexBuffer = ByteBuffer.allocateDirect(vertices.length * 4);
        indexBuffer.put(indices);
        indexBuffer.position(0);
    }

    public void draw() 
    {
        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(_glHandler.getPositionHandle(), COORDS_PER_VERTEX,
                                     GLES20.GL_FLOAT, false,
                                     vertexStride, vertexBuffer);

		GLES20.glEnableVertexAttribArray(_glHandler.getPositionHandle());
        
        // Set color for drawing the triangle
        GLES20.glUniform4fv(_glHandler.getColorHandle(), 1, color, 0);

        float rotationMatrix[] = new float[16];
        
        Matrix.setIdentityM(rotationMatrix, 0);
        Matrix.rotateM(rotationMatrix, 0, -20.0f, 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(rotationMatrix, 0, -20.0f, 1.0f, 0.0f, 0.0f);
        
        GLES20.glUniformMatrix4fv(_glHandler.getMVPMatrixHandle(), 1, false, rotationMatrix, 0);
        
        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 60, GLES20.GL_UNSIGNED_BYTE, indexBuffer);
        //GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(_glHandler.getPositionHandle());
    }
}
