package com.fARmework.modules.SpaceGraphics.Graphics.Models;

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
}
