package com.fARmework.modules.SpaceGraphics.Graphics;

import java.nio.*;

public abstract class Model
{	
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
	
	protected float _rotationMatrix[];
	
	protected Model(float width, float length, float height, float color[], float backgroundColor[])
	{		
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
	
	public void rotate(float xRotation, float yRotation, float zRotation) 
	{
		_xRotation = xRotation;
		_yRotation = yRotation;
		_zRotation = zRotation;
	}
	
	public void rotate(float[] rotation)
	{
		_rotationMatrix = rotation;
	}
	
	public float[] getRotationMatrix()
	{
		return _rotationMatrix;
	}
	
	public float[] getRotation()
	{
		float[] rotation = { _xRotation, _yRotation, _zRotation };
		
		return rotation;
	}
	
	public float[] getColor()
	{
		return _color;
	}
	
	public float[] getBackgroundColor()
	{
		return _backgroundColor;
	}
	
	public FloatBuffer getVertexBuffer()
	{
		return _vertexBuffer;
	}
	
	public ByteBuffer getIndexBuffer()
	{
		return _indexBuffer;
	}
	
	public int getVerticesAmount()
	{
		return _indices.length;
	}
}
