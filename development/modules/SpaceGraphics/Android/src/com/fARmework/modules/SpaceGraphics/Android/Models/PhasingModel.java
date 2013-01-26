package com.fARmework.modules.SpaceGraphics.Android.Models;

public abstract class PhasingModel extends Model
{
	protected float ColorRate;
	
	protected PhasingModel(float width, float length, float height, float[] backgroundColor)
	{
		super(width, length, height, backgroundColor);
		
		ColorRate = 1.0f;
	}

	public void setColorRate(float rate)
	{
		if (rate < 0.0f || rate > 1.0f)
		{
			return;
		}
		
		ColorRate = rate; 
		
		_colors = generateColors();
	}
}
