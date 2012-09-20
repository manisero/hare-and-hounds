package com.fARmework.modules.SpaceGraphics.Graphics.Models;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.GLES20;
import android.opengl.Matrix;

import com.fARmework.modules.SpaceGraphics.Graphics.GLHandler;
import com.fARmework.modules.SpaceGraphics.Graphics.IDrawable;
import com.fARmework.modules.SpaceGraphics.Graphics.IRotatable;

public abstract class Model implements IDrawable, IRotatable
{
	private final int COORDINATES_PER_VERTEX = 3;
	
	protected GLHandler _glHandler;
	
	protected float _width = 0.5f;
	protected float _length = 1.0f;
	protected float _height = 0.2f;
	
	protected float _vertices[];
	protected byte _indices[];
	
	protected float _color[];
	protected float _backgroundColor[];
	
	protected FloatBuffer _vertexBuffer;
	protected ByteBuffer _indexBuffer;
	
	protected float _xRotation = 0.0f;
	protected float _yRotation = 0.0f;
	protected float _zRotation = 0.0f;
	
	protected Model(GLHandler glHandler, float width, float length, float height, float color[], float backgroundColor[])
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
		generateIndices();
		initializeBuffers();
	}
	
	protected abstract void generateVertices();
	
	protected abstract void generateIndices();
	
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
	
	@Override
	public void draw() 
	{
		GLES20.glClearColor(_backgroundColor[0], 
							_backgroundColor[1],
							_backgroundColor[2],
							_backgroundColor[3]);
		
		GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);
		
        GLES20.glVertexAttribPointer(_glHandler.getPositionHandle(), 
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
}
