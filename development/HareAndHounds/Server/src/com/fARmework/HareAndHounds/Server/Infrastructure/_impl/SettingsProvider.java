package com.fARmework.HareAndHounds.Server.Infrastructure._impl;

import com.fARmework.HareAndHounds.Server.Infrastructure.*;

public class SettingsProvider implements ISettingsProvider
{
	@Override
	public int getPort()
	{
		return 6969;
	}

	@Override
	public int getGameRange()
	{
		return 1000;
	}

	@Override
	public int getCheckpointRange()
	{
		return 20;
	}
	
	@Override
	public int getHareDemandedPositionUpdateInterval()
	{
		return 20;
	}

	@Override
	public int getHoundsDemandedPositionUpdateInterval()
	{
		return 10;
	}

	@Override
	public int getRequiredInitialHarePositions()
	{
		return 3;
	}

	@Override
	public int getVictoriousHarePositions()
	{
		return 30;
	}
}
