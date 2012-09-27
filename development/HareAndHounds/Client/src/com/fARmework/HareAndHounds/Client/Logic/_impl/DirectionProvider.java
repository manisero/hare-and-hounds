package com.fARmework.HareAndHounds.Client.Logic._impl;

import com.fARmework.HareAndHounds.Client.Logic.*;

public class DirectionProvider implements IDirectionProvider
{
	private float _direction;

	@Override
	public float getDirection()
	{
		return _direction;
	}

	@Override
	public void setDirection(float direction)
	{
		_direction = direction;
	}
}
