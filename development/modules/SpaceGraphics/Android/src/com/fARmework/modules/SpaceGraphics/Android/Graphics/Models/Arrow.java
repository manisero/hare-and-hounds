package com.fARmework.modules.SpaceGraphics.Android.Graphics.Models;

import android.util.*;

import com.fARmework.modules.SpaceGraphics.Android.Graphics.*;

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
			// front arrow head
				
			 0.0f * _width, 	 0.5f * _length, 	 0.5f * _height,	// V0
			-0.5f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V1
			 0.5f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V6
			
			// front arrow base
			
			-0.3f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V2
			-0.3f * _width, 	-0.5f * _length, 	 0.5f * _height,	// V3
			 0.3f * _width, 	-0.5f * _length, 	 0.5f * _height,	// V4
			-0.3f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V2
			 0.3f * _width, 	-0.5f * _length, 	 0.5f * _height,	// V4
			 0.3f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V5
			
			// back arrow head
			
			 0.0f * _width, 	 0.5f * _length, 	-0.5f * _height,	// V7			
			 0.5f * _width,  	 0.1f * _length, 	-0.5f * _height,	// V13
			-0.5f * _width, 	 0.1f * _length, 	-0.5f * _height,	// V8
			
			// back arrow base
			
			 0.3f * _width, 	 0.1f * _length, 	-0.5f * _height,	// V12
			 0.3f * _width, 	-0.5f * _length, 	-0.5f * _height,	// V11
			-0.3f * _width, 	-0.5f * _length, 	-0.5f * _height,	// V10
			 0.3f * _width, 	 0.1f * _length, 	-0.5f * _height,	// V12
			-0.3f * _width, 	-0.5f * _length, 	-0.5f * _height,	// V10 
			-0.3f * _width,  	 0.1f * _length, 	-0.5f * _height,	// V9
			
			// left arrow slant
			
			-0.5f * _width, 	 0.1f * _length, 	-0.5f * _height,	// V8
			-0.5f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V1
			 0.0f * _width, 	 0.5f * _length, 	 0.5f * _height,	// V0
			-0.5f * _width, 	 0.1f * _length, 	-0.5f * _height,	// V8
			 0.0f * _width, 	 0.5f * _length, 	 0.5f * _height,	// V0
			 0.0f * _width, 	 0.5f * _length, 	-0.5f * _height,	// V7
			
			// right arrow slant 
			 0.0f * _width, 	 0.5f * _length, 	-0.5f * _height,	// V7
			 0.0f * _width, 	 0.5f * _length, 	 0.5f * _height,	// V0
			 0.5f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V6
			 0.0f * _width, 	 0.5f * _length, 	-0.5f * _height,	// V7
			 0.5f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V6
			 0.5f * _width, 	 0.1f * _length, 	-0.5f * _height,	// V13
			
			// left-bottom arrow head
			
			-0.5f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V1
			-0.5f * _width, 	 0.1f * _length, 	-0.5f * _height,	// V8
			-0.3f * _width,  	 0.1f * _length, 	-0.5f * _height,	// V9
			-0.5f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V1
			-0.3f * _width,  	 0.1f * _length, 	-0.5f * _height,	// V9
			-0.3f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V2
			
			// right-bottom arrow head
			
			 0.3f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V5
			 0.3f * _width, 	 0.1f * _length, 	-0.5f * _height,	// V12
			 0.5f * _width, 	 0.1f * _length, 	-0.5f * _height,	// V13
			 0.3f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V5
			 0.5f * _width, 	 0.1f * _length, 	-0.5f * _height,	// V13
			 0.5f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V6
			
			// bottom arrow base
			
			-0.3f * _width, 	-0.5f * _length, 	 0.5f * _height,	// V3
			-0.3f * _width, 	-0.5f * _length, 	-0.5f * _height,	// V10
			 0.3f * _width, 	-0.5f * _length, 	-0.5f * _height,	// V11
			-0.3f * _width, 	-0.5f * _length, 	 0.5f * _height,	// V3
			 0.3f * _width, 	-0.5f * _length, 	-0.5f * _height,	// V11
			 0.3f * _width, 	-0.5f * _length, 	 0.5f * _height,	// V4 
			
			// left arrow base
			
			-0.3f * _width,  	 0.1f * _length, 	-0.5f * _height,	// V9
			-0.3f * _width, 	-0.5f * _length, 	-0.5f * _height,	// V10
			-0.3f * _width, 	-0.5f * _length, 	 0.5f * _height,	// V3
			-0.3f * _width,  	 0.1f * _length, 	-0.5f * _height,	// V9
			-0.3f * _width, 	-0.5f * _length, 	 0.5f * _height,	// V3
			-0.3f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V2
			
			// right arrow base
			
			 0.3f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V5
			 0.3f * _width, 	-0.5f * _length, 	 0.5f * _height,	// V4
			 0.3f * _width, 	-0.5f * _length, 	-0.5f * _height,	// V11
			 0.3f * _width, 	 0.1f * _length, 	 0.5f * _height,	// V5
			 0.3f * _width, 	-0.5f * _length, 	-0.5f * _height,	// V11
			 0.3f * _width, 	 0.1f * _length, 	-0.5f * _height,	// V12
		};
		
		_vertices = vertices;
	}

	@Override
	protected void generateColors()
	{
		float[] colors =
		{
			// front arrow head
			
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
				
			// front arrow base
			
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			
			// back arrow head
			
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			
			// back arrow base
			
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			
			// left arrow slant
			
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			
			// right arrow slant 
			
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			
			// left-bottom arrow head
			
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			
			// right-bottom arrow head
			
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			
			// bottom arrow base
			
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
				
			// left arrow base
			
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
				
			// right arrow base
				
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,
			0.63671875f, 0.76953125f, 0.22265625f, 1.0f,			
		};
		
		_colors = colors;
	}

	@Override
	protected void generateNormals()
	{
		float[] normals = new float[_vertices.length];
		
		int vertices = _vertices.length / 9;
		
		for(int i = 0; i < vertices; ++i)
		{			
			float firstPoint[]	= {	_vertices[i * 9 + 0], 
									_vertices[i * 9 + 1], 
									_vertices[i * 9 + 2]	};
			
			float secondPoint[]	= {	_vertices[i * 9 + 3],
									_vertices[i * 9 + 4],
									_vertices[i * 9 + 5]	};
			
			float thirdPoint[]	= {	_vertices[i * 9 + 6],
									_vertices[i * 9 + 7], 
									_vertices[i * 9 + 8]	};
			
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
	
		_normals = normals;
	}
}
