package com.fARmework.modules.SpaceGraphics.Android.Graphics.Models;

import com.fARmework.modules.SpaceGraphics.Android.Graphics.*;

public class Arrow extends Model
{
	public Arrow()
	{
		this(0.5f, 1.0f, 0.2f, null, null);
	}
		
	public Arrow(float width, float length, float height, float[] color, float[] backgroundColor)
	{
		super(width, length, height, color, backgroundColor);
	}
	
	@Override
	protected void generateVertices()
	{
		// V0: 0.0f * _width, 0.5f * _length, 0.5f * _height,
		// V1: -0.5f * _width, 0.1f * _length, 0.5f * _height,
		// V2: -0.3f * _width, 0.1f * _length, 0.5f * _height,
		// V3: -0.3f * _width, -0.5f * _length, 0.5f * _height,
		// V4: 0.3f * _width, -0.5f * _length, 0.5f * _height,
		// V5: 0.3f * _width, 0.1f * _length, 0.5f * _height,
		// V6: 0.5f * _width, 0.1f * _length, 0.5f * _height,
		// V7: 0.0f * _width, 0.5f * _length, -0.5f * _height,
		// V8: 0.5f * _width, 0.1f * _length, -0.5f * _height,
		// V9: 0.3f * _width, 0.1f * _length, -0.5f * _height,
		// V10: 0.3f * _width, -0.5f * _length, -0.5f * _height,
		// V11: -0.3f * _width, -0.5f * _length, -0.5f * _height,
		// V12: -0.3f * _width, 0.1f * _length, -0.5f * _height,
		// V13: -0.5f * _width, 0.1f * _length, -0.5f * _height,
		
		float[] vertices =
		{
				// front arrow head
				
				0.0f * _width, 0.5f * _length, 0.5f * _height,
				-0.5f * _width, 0.1f * _length, 0.5f * _height,
				0.5f * _width, 0.1f * _length, 0.5f * _height,
				
				// front arrow base
				
				-0.3f * _width, 0.1f * _length, 0.5f * _height,
				-0.3f * _width, -0.5f * _length, 0.5f * _height,
				0.3f * _width, -0.5f * _length, 0.5f * _height,
				-0.3f * _width, 0.1f * _length, 0.5f * _height,
				0.3f * _width, -0.5f * _length, 0.5f * _height,
				0.3f * _width, 0.1f * _length, 0.5f * _height,
				
				// back arrow head
				
				0.0f * _width, 0.5f * _length, -0.5f * _height,				
				-0.5f * _width, 0.1f * _length, -0.5f * _height,
				0.5f * _width, 0.1f * _length, -0.5f * _height,
				
				// back arrow base
				
				-0.3f * _width, 0.1f * _length, -0.5f * _height,				
				-0.3f * _width, -0.5f * _length, -0.5f * _height,
				0.3f * _width, -0.5f * _length, -0.5f * _height,
				-0.3f * _width, 0.1f * _length, -0.5f * _height,
				0.3f * _width, -0.5f * _length, -0.5f * _height,
				0.3f * _width, 0.1f * _length, -0.5f * _height,
				
				// left arrow slant
				
				0.0f * _width, 0.5f * _length, -0.5f * _height,
				0.5f * _width, 0.1f * _length, -0.5f * _height,
				-0.5f * _width, 0.1f * _length, 0.5f * _height,
				0.0f * _width, 0.5f * _length, -0.5f * _height,
				-0.5f * _width, 0.1f * _length, 0.5f * _height,
				0.0f * _width, 0.5f * _length, 0.5f * _height,
				
				// right arrow slant 
				
				0.0f * _width, 0.5f * _length, 0.5f * _height,
				0.5f * _width, 0.1f * _length, 0.5f * _height,
				-0.5f * _width, 0.1f * _length, -0.5f * _height,
				0.0f * _width, 0.5f * _length, 0.5f * _height,
				-0.5f * _width, 0.1f * _length, -0.5f * _height,
				0.0f * _width, 0.5f * _length, -0.5f * _height,
				
				// left-bottom arrow head
				
				-0.5f * _width, 0.1f * _length, 0.5f * _height,
				0.5f * _width, 0.1f * _length, -0.5f * _height,
				0.3f * _width, 0.1f * _length, -0.5f * _height,
				-0.5f * _width, 0.1f * _length, 0.5f * _height,
				0.3f * _width, 0.1f * _length, -0.5f * _height,
				-0.3f * _width, 0.1f * _length, 0.5f * _height,
				
				// right-bottom arrow head
				
				0.3f * _width, 0.1f * _length, 0.5f * _height,				
				-0.3f * _width, 0.1f * _length, -0.5f * _height,
				-0.5f * _width, 0.1f * _length, -0.5f * _height,
				0.3f * _width, 0.1f * _length, 0.5f * _height,
				-0.5f * _width, 0.1f * _length, -0.5f * _height,
				0.5f * _width, 0.1f * _length, 0.5f * _height,
				
				// bottom arrow base
				
				-0.3f * _width, -0.5f * _length, 0.5f * _height,
				0.3f * _width, -0.5f * _length, -0.5f * _height,
				-0.3f * _width, -0.5f * _length, -0.5f * _height,
				-0.3f * _width, -0.5f * _length, 0.5f * _height,
				-0.3f * _width, -0.5f * _length, -0.5f * _height,
				0.3f * _width, -0.5f * _length, 0.5f * _height,
				
				// left arrow base
				
				0.3f * _width, 0.1f * _length, -0.5f * _height,
				0.3f * _width, -0.5f * _length, -0.5f * _height,
				-0.3f * _width, -0.5f * _length, 0.5f * _height,
				0.3f * _width, 0.1f * _length, -0.5f * _height,
				-0.3f * _width, -0.5f * _length, 0.5f * _height,
				-0.3f * _width, 0.1f * _length, 0.5f * _height,
				
				// right arrow base
				
				0.3f * _width, 0.1f * _length, 0.5f * _height,		
				0.3f * _width, -0.5f * _length, 0.5f * _height,
				-0.3f * _width, -0.5f * _length, -0.5f * _height,
				0.3f * _width, 0.1f * _length, 0.5f * _height,
				-0.3f * _width, -0.5f * _length, -0.5f * _height,
				-0.3f * _width, 0.1f * _length, -0.5f * _height,				
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
}
