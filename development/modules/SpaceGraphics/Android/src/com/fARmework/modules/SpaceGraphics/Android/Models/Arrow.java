package com.fARmework.modules.SpaceGraphics.Android.Models;

public class Arrow extends PhasingModel
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
	protected float[] generateVertices()
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
		
		return vertices;
	}

	@Override
	protected float[] generateColors()
	{
		float restRate = 1.0f - ColorRate;
		
		float[] colors =
		{
			// front arrow head
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
				
			// front arrow base
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			
			// back arrow head
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			
			// back arrow base
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			
			// left arrow slant
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			
			// right arrow slant
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			
			// left-bottom arrow head
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			
			// right-bottom arrow head
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			
			// bottom arrow base
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
				
			// left arrow base
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
				
			// right arrow base
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,
			1.0f * restRate,	1.0f * ColorRate,	0.0f,	1.0f,		
		};
		
		return colors;
	}
}
