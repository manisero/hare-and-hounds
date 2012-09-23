package com.fARmework.modules.SpaceGraphics.Graphics;

import java.nio.*;

public abstract class Model
{	
	protected float _width = 0.5f;
	protected float _length = 1.0f;
	protected float _height = 0.2f;
	
	protected float[] _vertices;
	protected byte[] _indices;
	protected float[] _colors;
	protected float[] _normals;
	
	protected float[] _backgroundColor;
	
	protected FloatBuffer _vertexBuffer;
	protected ByteBuffer _indexBuffer;
	protected FloatBuffer _colorBuffer;
	protected FloatBuffer _normalBuffer;
	
	protected Model(float width, float length, float height, float[] backgroundColor)
	{		
		_width = width;
		_length = length;
		_height = height;
		
		if(backgroundColor == null)
		{
			float[] backgroundColorArray = { 1.0f, 1.0f, 1.0f, 1.0f };
			_backgroundColor = backgroundColorArray;
		}
		
		generateVertices();
		generateIndices();
		generateColors();
		generateNormals();
		initializeBuffers();
	}
	
	protected abstract void generateVertices();
	
	protected abstract void generateIndices();
	
	protected abstract void generateColors();
	
	protected abstract void generateNormals();
	
	private void initializeBuffers()
	{
		ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(_vertices.length * 4);
		vertexByteBuffer.order(ByteOrder.nativeOrder());
		
		_vertexBuffer = vertexByteBuffer.asFloatBuffer();
		_vertexBuffer.put(_vertices).position(0);
		
		_indexBuffer = ByteBuffer.allocateDirect(_indices.length * 4);
		_indexBuffer.put(_indices).position(0);
		
		ByteBuffer colorByteBuffer = ByteBuffer.allocateDirect(_colors.length * 4);
		colorByteBuffer.order(ByteOrder.nativeOrder());
		
		_colorBuffer = colorByteBuffer.asFloatBuffer();
		_colorBuffer.put(_colors).position(0);
		
		ByteBuffer normalByteBuffer = ByteBuffer.allocateDirect(_normals.length * 4);
		normalByteBuffer.order(ByteOrder.nativeOrder());
		
		_normalBuffer = normalByteBuffer.asFloatBuffer();
		_normalBuffer.put(_normals).position(0);
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
	
	public FloatBuffer getColorBuffer()
	{
		return _colorBuffer;
	}
	
	public FloatBuffer getNormalBuffer()
	{
		return _normalBuffer;
	}
	
	public int getVerticesAmount()
	{
		return _indices.length;
	}
}
