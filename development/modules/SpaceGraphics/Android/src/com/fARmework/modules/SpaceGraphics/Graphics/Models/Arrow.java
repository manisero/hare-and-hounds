package com.fARmework.modules.SpaceGraphics.Graphics.Models;

import android.util.*;

import com.fARmework.modules.SpaceGraphics.Graphics.*;

public class Arrow extends Model
{
	public Arrow()
	{
		this(0.5f, 1.0f, 0.2f, null);
	}
		
	public Arrow(float width, float length, float height, float[] backgroundColor)
	{
		super(width, length, height, backgroundColor);
	}
	
	@Override
	protected void generateVertices()
	{
		float[] vertices =
		{
			 0.00f * _width,	 0.50f * _length,	 0.50f * _height,	 
			 0.50f * _width,	 0.10f * _length,	 0.50f * _height,	 
			 0.30f * _width,	 0.10f * _length,	 0.50f * _height,	 
			 0.30f * _width,	-0.50f * _length,	 0.50f * _height,	 
			-0.30f * _width,	-0.50f * _length,	 0.50f * _height,	
			-0.30f * _width,	 0.10f * _length,	 0.50f * _height,	
			-0.50f * _width,	 0.10f * _length,	 0.50f * _height,	
			 0.00f * _width,	 0.50f * _length,	-0.50f * _height,	 
			 0.50f * _width,	 0.10f * _length,	-0.50f * _height,	 
			 0.30f * _width,	 0.10f * _length,	-0.50f * _height,	 
			 0.30f * _width,	-0.50f * _length,	-0.50f * _height,	 
			-0.30f * _width,	-0.50f * _length,	-0.50f * _height,	
			-0.30f * _width,	 0.10f * _length,	-0.50f * _height,	
			-0.50f * _width,	 0.10f * _length,	-0.50f * _height				
		};
		
		_vertices = vertices;
	}
	
	@Override
	protected void generateIndices()
	{
		byte[] indices =
		{
			 0,	 1,	 6,	 		 2,	 3,	 5,
			 3,	 4,	 5,			 7,	13,	 8,
			 9,	12,	10,			12,	11,	10,
			 0,	 7,	 8, 		 0,	 8,	 1,
			 1,	 8,	13,			 1,	13,	 6,
			 2,	 9,	10, 		 2,	10,	 3,
			 3,	10,	11, 		11,	 4,	 3,
			 4,	11,	12,			 4,	12,	 5,
			 2,	 5,	 9, 		 9,	 5,	12,
			 6,	13,	 7, 		 6,	 7,	 0    			
		};
		
		_indices = indices;
	}

	@Override
	protected void generateColors()
	{
		float[] colors =
		{
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f,
			0.63671875f, 	0.76953125f, 	0.22265625f, 	1.0f
		};
		
		_colors = colors;
	}

	@Override
	protected void generateNormals()
	{
		float[] normals = new float[_indices.length];
		
		int vertices = _indices.length / 3;
		
		for(int i = 0; i < vertices; ++i)
		{
			int firstPointPosition 	= _indices[i * 3 + 2];
			int secondPointPosition = _indices[i * 3 + 1];
			int thirdPointPosition	= _indices[i * 3 + 0];
			
			
			float firstPoint[]	= {	_vertices[firstPointPosition * 3 + 0], 
									_vertices[firstPointPosition * 3 + 1], 
									_vertices[firstPointPosition * 3 + 2]	};
			
			float secondPoint[]	= {	_vertices[secondPointPosition * 3 + 0],
									_vertices[secondPointPosition * 3 + 1],
									_vertices[secondPointPosition * 3 + 2]	};
			
			float thirdPoint[]	= {	_vertices[thirdPointPosition * 3 + 0],
									_vertices[thirdPointPosition * 3 + 1], 
									_vertices[thirdPointPosition * 3 + 2]	};
			
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
			
			normals[i * 3 + 0] = N[0];
			normals[i * 3 + 1] = N[1];
			normals[i * 3 + 2] = N[2];
		}
	
		_normals = normals;
		
		for(int i = 0; i < vertices; ++i)
		{
			Log.d("NORMALS", "" + i + ": " + normals[i * 3] + ", " + normals[i * 3 + 1] + ", " + normals[i * 3 + 2]);
		}
	}
}
