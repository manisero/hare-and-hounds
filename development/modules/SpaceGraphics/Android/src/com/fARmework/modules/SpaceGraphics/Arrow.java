package com.fARmework.modules.SpaceGraphics;

import android.opengl.*;

import java.nio.*;

public class Arrow implements IDrawable, IRotatable
{
	private GLHandler _glHandler;
	
	private float _width = 0.5f;
	private float _length = 1.0f;
	private float _height = 0.2f;
	
	private float _vertices[];
	
	private byte _indices[] = 
	{
		0,	1,	6,			2,	3,	5,
		3,	4,	5,			7,	13,	8,
		9,	12,	10,			12,	11,	10,
		0,	7,	8, 			0,	8,	1,
		1,	8,	9,			1,	9,	2,
		2,	9,	10, 		2,	10,	3,
		3,	10,	11, 		3,	11,	4,
		4,	11,	12,			4,	12,	5,
		5,	12,	13, 		5,	13,	6,
		6,	13,	7, 			6,	7,	0    			
	};
	
	private float _color[];
	private float _backgroundColor[];
	
	private FloatBuffer _vertexBuffer;
	private ByteBuffer _indexBuffer;
	
	private float _xRotation = 0.0f;
	private float _yRotation = 0.0f;
	private float _zRotation = 0.0f;
	
	public Arrow(GLHandler glHandler)
	{
		this(glHandler, 0.5f, 1.0f, 0.2f, null, null);
	}
		
	public Arrow(GLHandler glHandler, float width, float length, float height, float color[], float backgroundColor[])
	{
		_glHandler = glHandler;
		
		_width = width;
		_length = length;
		_height = height;
		
		if(color == null)
		{
			float colorArray[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };
			_color = colorArray;
		}
		
		if(backgroundColor == null)
		{
			float backgroundColorArray[] = { 1.0f, 1.0f, 1.0f, 1.0f };
			_backgroundColor = backgroundColorArray;
		}
		
		generateVertices();
		initializeBuffers();
	}
	
	private void generateVertices()
	{
		float vertices[] =
		{
			-0.50f * _length,	 0.50f * _height,	 0.00f * _width,
			-0.10f * _length,	 0.50f * _height,	 0.50f * _width,
			-0.10f * _length,	 0.50f * _height,	 0.30f * _width,
			 0.50f * _length,	 0.50f * _height,	 0.30f * _width,
			 0.50f * _length,	 0.50f * _height,	-0.30f * _width,
			-0.10f * _length,	 0.50f * _height,	-0.30f * _width,
			-0.10f * _length,	 0.50f * _height,	-0.50f * _width,
			-0.50f * _length,	-0.50f * _height,	 0.00f * _width,
			-0.10f * _length,	-0.50f * _height,	 0.50f * _width,
			-0.10f * _length,	-0.50f * _height,	 0.30f * _width,
			 0.50f * _length,	-0.50f * _height,	 0.30f * _width,
			 0.50f * _length,	-0.50f * _height,	-0.30f * _width,
			-0.10f * _length,	-0.50f * _height,	-0.30f * _width,
			-0.10f * _length,	-0.50f * _height,	-0.50f * _width			
		};
		
		_vertices = vertices;
	}
	
	private void initializeBuffers()
	{
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(_vertices.length * 4);
		byteBuffer.order(ByteOrder.nativeOrder());
		
		_vertexBuffer = byteBuffer.asFloatBuffer();
		_vertexBuffer.put(_vertices);
		_vertexBuffer.position(0);
		
		_indexBuffer = ByteBuffer.allocateDirect(_indices.length * 4);
		_indexBuffer.put(_indices);
		_indexBuffer.position(0);
	}

	private final int COORDINATES_PER_VERTEX = 3;
	
	@Override
	public void draw() 
	{
		GLES20.glClearColor(	_backgroundColor[0], 
								_backgroundColor[1],
								_backgroundColor[2],
								_backgroundColor[3]);
		
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		
        GLES20.glVertexAttribPointer(	_glHandler.getPositionHandle(), 
        								COORDINATES_PER_VERTEX,
        								GLES20.GL_FLOAT,
        								false,
        								COORDINATES_PER_VERTEX * 4, 
        								_vertexBuffer);

		GLES20.glEnableVertexAttribArray(_glHandler.getPositionHandle());
        
        GLES20.glUniform4fv(_glHandler.getColorHandle(), 1, _color, 0);

	    float rotationMatrix[] = new float[16];
	        
	    Matrix.setIdentityM(rotationMatrix, 0);
	    
	    Matrix.rotateM(rotationMatrix, 0, _xRotation, 1.0f, 0.0f, 0.0f);
	    Matrix.rotateM(rotationMatrix, 0, _yRotation, 0.0f, 1.0f, 0.0f);
	    Matrix.rotateM(rotationMatrix, 0, _zRotation, 0.0f, 0.0f, 1.0f);
	        
	    GLES20.glUniformMatrix4fv(_glHandler.getMVPMatrixHandle(), 1, false, rotationMatrix, 0);
	        
	    GLES20.glDrawElements(GLES20.GL_TRIANGLES, 60, GLES20.GL_UNSIGNED_BYTE, _indexBuffer);

        GLES20.glDisableVertexAttribArray(_glHandler.getPositionHandle());		
	}
	
	@Override
	public void rotate(float xRotation, float yRotation, float zRotation) 
	{
		_xRotation = xRotation;
		_yRotation = yRotation;
		_zRotation = zRotation;
	}

	@Override
	public void setViewport(int width, int height) 
	{
		GLES20.glViewport(0, 0, width, height);
	}
}
