package com.fARmework.modules.SpaceGraphics.Graphics;

import android.opengl.*;

public class GLHandler 
{
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
	
	private final int _program;
	private int _positionHandle;
	private int _colorHandle;
	private int _MVPMatrixHandle;
	
	public GLHandler()
	{
		_program = GLES20.glCreateProgram();
		
		initialize();
	}
	
	public int getVertexShader()
	{
		return _vertexShader;
	}
	
	public int getFragmentShader()
	{
		return _fragmentShader;
	}
	
	public int getProgram()
	{
		return _program;
	}
	
	public int getPositionHandle()
	{
		return _positionHandle;
	}
	
	public int getColorHandle()
	{
		return _colorHandle;
	}
	
	public int getMVPMatrixHandle()
	{
		return _MVPMatrixHandle;
	}
	
	private void initialize()
	{		
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
}
