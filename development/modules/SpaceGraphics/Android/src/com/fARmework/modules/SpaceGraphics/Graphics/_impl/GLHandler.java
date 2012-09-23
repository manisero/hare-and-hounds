package com.fARmework.modules.SpaceGraphics.Graphics._impl;

import com.fARmework.modules.SpaceGraphics.Graphics.*;

import android.opengl.*;

public class GLHandler implements IGLHandler 
{
	private final int COORDINATES_PER_VERTEX = 3;
	private final int COLORS_PER_VERTEX = 4;
	
	private final String VERTEX_SHADER_CODE =
			"uniform mat4 u_MVPMatrix; " +
			"uniform mat4 u_MVMatrix; " +
			"uniform vec3 u_LightPos; " +
			"attribute vec4 a_Position; " +
			"attribute vec4 a_Color; " +
			"attribute vec3 a_Normal; " +
			"varying vec4 v_Color; " +
			"void main() " +
			"{" +
			"	vec3 modelViewVertex = vec3(u_MVMatrix * a_Position); " +
			"	vec3 modelViewNormal = vec3(u_MVMatrix * vec4(a_Normal, 0.0)); " +
			"	float distance = length(u_LightPos - modelViewVertex); " +
			"	vec3 lightVector = normalize(u_LightPos - modelViewVertex); " +
			"	float diffuse = max(dot(modelViewNormal, lightVector), 0.1); " +
			"	diffuse = diffuse * (1.0 / (1.0 + (0.25 * distance * distance))); " +
			"	v_Color = a_Color * diffuse; " +
			"	gl_Position = u_MVPMatrix * a_Position;" +
			"}";
	
	private final String FRAGMENT_SHADER_CODE =
			"precision mediump float; " +
			"varying vec4 v_Color; " +
			"void main()" +
			"{" +
			"	gl_FragColor = v_Color;" +
			"}";
	
	private int _vertexShader;
	private int _fragmentShader;
	
	private int _program;
	private int _positionHandle;
	private int _colorHandle;
	private int _normalHandle;
	private int _lightPositionHandle;
	private int _MVPMatrixHandle;
	private int _MVMatrixHandle;
	
	private float[] _lightPosition = { 0.0f, 0.2f, 3.0f };
	
	private float[] _projectionMatrix = new float[16];
	private float[] _viewMatrix = new float[16];
	private float[] _viewProjectionMatrix = new float[16];
	
	private float[] _rotationMatrix = new float[16];
	private float _direction = 0.0f;
	
	@Override
	public void initialize()
	{
		_program = GLES20.glCreateProgram();
		
		_vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, VERTEX_SHADER_CODE);
		_fragmentShader = loadShader(GLES20.GL_FRAGMENT_SHADER, FRAGMENT_SHADER_CODE);
		
		GLES20.glBindAttribLocation(_program, 0, "a_Position");
		GLES20.glBindAttribLocation(_program, 1, "a_Color");
		GLES20.glBindAttribLocation(_program, 2, "a_Normal");
		
		GLES20.glAttachShader(_program, _vertexShader);
		GLES20.glAttachShader(_program, _fragmentShader);
		GLES20.glLinkProgram(_program);
		
		GLES20.glUseProgram(_program);
		
        _MVPMatrixHandle = GLES20.glGetUniformLocation(_program, "u_MVPMatrix");	
        _MVMatrixHandle = GLES20.glGetUniformLocation(_program, "u_MVMatrix");
        _lightPositionHandle = GLES20.glGetUniformLocation(_program, "u_LightPos");
        
		_positionHandle = GLES20.glGetAttribLocation(_program, "a_Position");
        _colorHandle = GLES20.glGetAttribLocation(_program, "a_Color");
        _normalHandle = GLES20.glGetAttribLocation(_program, "a_Normal");
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
				
		Matrix.setIdentityM(_projectionMatrix, 0);
	}
	
	@Override
	public void setRotationMatrix(float[] rotationMatrix)
	{
		_rotationMatrix = rotationMatrix;
	}
	
	@Override
	public void setDirection(float direction)
	{
		_direction = direction;
	}	
	
	@Override
	public void draw(Model model) 
	{
		float[] backgroundColor = model.getBackgroundColor();
		
		GLES20.glClearColor(backgroundColor[0], 
							backgroundColor[1],
							backgroundColor[2],
							backgroundColor[3]);
		
		GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
		
		GLES20.glEnable(GLES20.GL_CULL_FACE);
		GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		
        GLES20.glVertexAttribPointer(_positionHandle, 
        							 COORDINATES_PER_VERTEX,
        							 GLES20.GL_FLOAT,
        							 false,
        							 0, 
        							 model.getVertexBuffer());
        
        GLES20.glVertexAttribPointer(_colorHandle, 
        							 COLORS_PER_VERTEX, 
        							 GLES20.GL_FLOAT, 
        							 false, 
        							 0, 
        							 model.getColorBuffer());
        
        GLES20.glVertexAttribPointer(_normalHandle, 
        							 COORDINATES_PER_VERTEX,
        							 GLES20.GL_FLOAT,
        							 false,
        							 0,
        							 model.getNormalBuffer());

        GLES20.glEnableVertexAttribArray(_normalHandle);
        GLES20.glEnableVertexAttribArray(_colorHandle);
		GLES20.glEnableVertexAttribArray(_positionHandle);
        	    
			Matrix.setIdentityM(_viewMatrix, 0);
			
			GLES20.glUniformMatrix4fv(_MVMatrixHandle, 1, false, _viewMatrix, 0);
		
        	Matrix.multiplyMM(_viewProjectionMatrix, 0, _projectionMatrix, 0, _rotationMatrix, 0);
        	Matrix.rotateM(_viewProjectionMatrix, 0, _direction, 0.0f, 0.0f, 1.0f);
        	
        	GLES20.glUniformMatrix4fv(_MVPMatrixHandle, 1, false, _viewProjectionMatrix, 0);        
	       
        	GLES20.glUniform3f(_lightPositionHandle, _lightPosition[0], _lightPosition[1], _lightPosition[2]);
        	
        	GLES20.glDrawElements(GLES20.GL_TRIANGLES, model.getVerticesAmount(), GLES20.GL_UNSIGNED_BYTE, model.getIndexBuffer());

        GLES20.glDisableVertexAttribArray(_positionHandle);
        GLES20.glDisableVertexAttribArray(_colorHandle);
        GLES20.glDisableVertexAttribArray(_normalHandle);
	}
}
