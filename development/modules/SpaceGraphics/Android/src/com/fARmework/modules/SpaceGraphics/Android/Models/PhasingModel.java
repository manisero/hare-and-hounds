package com.fARmework.modules.SpaceGraphics.Android.Models;

public abstract class PhasingModel extends Model
{
	protected float _rate;
	
	protected PhasingModel(float width, float length, float height, float[] backgroundColor)
	{
		super(width, length, height, backgroundColor);
		
		_rate = 1.0f;
	}

	public void setColorRate(float rate)
	{
		if(rate < 0.0f || rate > 1.0f)
		{
			return;
		}
		
		_rate = rate; 
		
		generateColors();
	}
}
