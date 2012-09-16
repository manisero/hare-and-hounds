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

    public static int loadShader(int type, String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)
        int shader = GLES20.glCreateShader(type);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(shader, shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

}

class Triangle {

    private final String vertexShaderCode =
    	"uniform mat4 u_MVPMatrix; " +
        "attribute vec4 vPosition;" +
        "void main() {" +
        "  gl_Position = u_MVPMatrix * vPosition;" +
        "}";

    private final String fragmentShaderCode =
        "precision mediump float;" +
        "uniform vec4 vColor;" +
        "void main() {" +
        "  gl_FragColor = vColor;" +
        "}";

    private final FloatBuffer vertexBuffer;
    private final ByteBuffer indexBuffer;
    private final int mProgram;
    private int mPositionHandle;
    private int mColorHandle;
    private int mMVPMatrixHandle;

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

        // prepare shaders and OpenGL program
        int vertexShader = GraphicsRenderer.loadShader(GLES20.GL_VERTEX_SHADER,
                                                   vertexShaderCode);
        int fragmentShader = GraphicsRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER,
                                                     fragmentShaderCode);

        mProgram = GLES20.glCreateProgram();             // create empty OpenGL Program
        GLES20.glAttachShader(mProgram, vertexShader);   // add the vertex shader to program
        GLES20.glAttachShader(mProgram, fragmentShader); // add the fragment shader to program
        GLES20.glLinkProgram(mProgram);                  // create OpenGL program executables

    }

    public void draw() {
        // Add program to OpenGL environment
        GLES20.glUseProgram(mProgram);

        // get handle to vertex shader's vPosition member
        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition");

        // Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(mPositionHandle);

        // Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(mPositionHandle, COORDS_PER_VERTEX,
                                     GLES20.GL_FLOAT, false,
                                     vertexStride, vertexBuffer);

        // get handle to fragment shader's vColor member
        mColorHandle = GLES20.glGetUniformLocation(mProgram, "vColor");
        
        mMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "u_MVPMatrix");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(mColorHandle, 1, color, 0);

        float rotationMatrix[] = new float[16];
        
        Matrix.setIdentityM(rotationMatrix, 0);
        Matrix.rotateM(rotationMatrix, 0, -20.0f, 0.0f, 1.0f, 0.0f);
        Matrix.rotateM(rotationMatrix, 0, -20.0f, 1.0f, 0.0f, 0.0f);
        
        GLES20.glUniformMatrix4fv(mMVPMatrixHandle, 1, false, rotationMatrix, 0);
        
        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 60, GLES20.GL_UNSIGNED_BYTE, indexBuffer);
        //GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(mPositionHandle);
    }
}
