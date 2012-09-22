package com.fARmework.modules.SpaceGraphics.Graphics._impl;

import com.fARmework.modules.SpaceGraphics.Graphics.*;

import android.opengl.*;

public class GLHandler implements IGLHandler 
{
	private final int COORDINATES_PER_VERTEX = 3;
	
	private final String VERTEX_SHADER_CODE =
			"uniform mat4 u_MVPMatrix; " +
			"attribute vec4 vPosition;" +
			"void main() " +
			"{" +
			"	gl_Position = u_MVPMatrix * vPosition;" +
			"}";
	
	private final String FRAGMENT_SHADER_CODE =
			"precision mediump float;" +
			"uniform vec4 vColor;" +
			"void main()" +
			"{" +
			"	gl_FragColor = vColor;" +
			"}";
	
	private int _vertexShader;
	private int _fragmentShader;
	
	private int _program;
	private int _positionHandle;
	private int _colorHandle;
	private int _MVPMatrixHandle;
	
	private float[] _projectionMatrix = new float[16];
	private float[] _viewProjectionMatrix = new float[16];
	private float[] _rotationMatrix = new float[16];
	
	@Override
	public void initialize()
	{
		_program = GLES20.glCreateProgram();
		
		_vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER_CODE);
		_fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER_CODE);
		
		GLES20.glAttachShader(_program, _vertexShader);
		GLES20.glAttachShader(_program, _fragmentShader);
		GLES20.glLinkProgram(_program);
		
		GLES20.glUseProgram(_program);
		
		_positionHandle = GLES20.glGetAttribLocation(_program, "vPosition");
		
        _colorHandle = GLES20.glGetUniformLocation(_program, "vColor");
        _MVPMatrixHandle = GLES20.glGetUniformLocation(_program, "u_MVPMatrix");
	}
	
	private int loadShader(int type, String code)
	{
		int shader = GLES20.glCreateShader(type);
		
		GLES20.glShaderSource(shader, code);
		GLES20.glCompileShader(shader);
		
		return shader;
	}
	
	@Override
	public void setViewport(int width, int height) 
	{
		GLES20.glViewport(0, 0, width, height);
		
		/*float ratio = (float) width / height;
		Matrix.frustumM(_projectionMatrix, 0, -ratio, ratio, -1, 1, 1, 7);*/
		
		Matrix.setIdentityM(_projectionMatrix, 0);
	}
	
	@Override
	public void setRotationMatrix(float[] rotationMatrix)
	{
		_rotationMatrix = rotationMatrix;
	}
	
	@Override
	public void draw(Model model) 
	{
		float[] backgroundColor = model.getBackgroundColor();
		
		GLES20.glClearColor(backgroundColor[0], 
							backgroundColor[1],
							backgroundColor[2],
							backgroundColor[3]);
		
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		
        GLES20.glVertexAttribPointer(_positionHandle, 
        							 COORDINATES_PER_VERTEX,
        							 GLES20.GL_FLOAT,
        							 false,
        							 COORDINATES_PER_VERTEX * 4, 
        							 model.getVertexBuffer());

		GLES20.glEnableVertexAttribArray(_positionHandle);
        
        	GLES20.glUniform4fv(_colorHandle, 1, model.getColor(), 0);
	    
        	Matrix.multiplyMM(_viewProjectionMatrix, 0, _projectionMatrix, 0, _rotationMatrix, 0);
        	GLES20.glUniformMatrix4fv(_MVPMatrixHandle, 1, false, _viewProjectionMatrix, 0);        
	        
        	GLES20.glDrawElements(GLES20.GL_TRIANGLES, model.getVerticesAmount(), GLES20.GL_UNSIGNED_BYTE, model.getIndexBuffer());

        GLES20.glDisableVertexAttribArray(_positionHandle);
	}
}
