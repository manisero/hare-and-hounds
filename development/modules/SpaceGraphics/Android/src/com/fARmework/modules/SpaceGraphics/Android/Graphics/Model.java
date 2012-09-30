package com.fARmework.modules.SpaceGraphics.Android.Graphics;

public abstract class Model
{	
	protected float _width = 0.5f;
	protected float _length = 1.0f;
	protected float _height = 0.2f;
	
	protected float[] _vertices;
	protected float[] _colors;
	protected float[] _normals;

	protected float[] _backgroundColor;
	
	protected Model(float width, float length, float height, float[] backgroundColor)
	{		
		_width = width;
		_length = length;
		_height = height;
		
		if(backgroundColor == null)
		{
			float[] backgroundColorArray = { 0.0f, 0.0f, 0.0f, 0.0f };
			_backgroundColor = backgroundColorArray;
		}
		
		generateVertices();
		generateColors();
		generateNormals();
	}
	
	protected abstract void generateVertices();
	protected abstract void generateColors();
	protected abstract void generateNormals();
	
	public float[] getBackgroundColor()
	{
		return _backgroundColor;
	}
	
	public float[] getVertices()
	{
		return _vertices;
	}
	
	public float[] getColors()
	{
		return _colors;
	}
	
	public float[] getNormals()
	{
		return _normals;
	}
	
	public int getVerticesAmount()
	{
		return _vertices.length / 3;
	}
}
