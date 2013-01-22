package com.fARmework.modules.SpaceGraphics.Android.Models;

import android.util.*;

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
		
		if (backgroundColor == null)
		{
			float[] backgroundColorArray = { 0.0f, 0.0f, 0.0f, 0.0f };
			_backgroundColor = backgroundColorArray;
		}
		
		_vertices = generateVertices();
		_colors = generateColors();
		_normals = generateNormals(_vertices);
	}
	
	protected abstract float[] generateVertices();
	
	protected abstract float[] generateColors();
	
	protected float[] generateNormals(float[] vertices)
	{
		float[] normals = new float[vertices.length];
		
		int verticesCount = vertices.length / 9;
		
		for(int i = 0; i < verticesCount; ++i)
		{			
			float firstPoint[]	= {	vertices[i * 9 + 0], 
									vertices[i * 9 + 1], 
									vertices[i * 9 + 2]	};
			
			float secondPoint[]	= {	vertices[i * 9 + 3],
									vertices[i * 9 + 4],
									vertices[i * 9 + 5]	};
			
			float thirdPoint[]	= {	vertices[i * 9 + 6],
									vertices[i * 9 + 7], 
									vertices[i * 9 + 8]	};
			
			float U[]	=	{	secondPoint[0] - firstPoint[0],
								secondPoint[1] - firstPoint[1],
								secondPoint[2] - firstPoint[2]	};
			
			float V[]	=	{	thirdPoint[0] - firstPoint[0],
								thirdPoint[1] - firstPoint[1],
								thirdPoint[2] - firstPoint[2]	};
			
			float N[]	=	{	U[1] * V[2] - U[2] * V[1],
								U[2] * V[0] - U[0] * V[2],
								U[0] * V[1] - U[1] * V[0]	};

			float length = FloatMath.sqrt((N[0] * N[0]) + (N[1] * N[1]) + (N[2] * N[2]));
			
			N[0] = N[0] / length;
			N[1] = N[1] / length;
			N[2] = N[2] / length;
			
			normals[i * 9 + 0] = N[0];
			normals[i * 9 + 1] = N[1];
			normals[i * 9 + 2] = N[2];
			normals[i * 9 + 3] = N[0];
			normals[i * 9 + 4] = N[1];
			normals[i * 9 + 5] = N[2];
			normals[i * 9 + 6] = N[0];
			normals[i * 9 + 7] = N[1];
			normals[i * 9 + 8] = N[2];
		}
	
		return normals;
	}
	
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
