package com.fARmework.HareAndHounds.Server.Infrastructure;

public interface ISettingsProvider extends com.fARmework.core.server.Infrastructure.ISettingsProvider
{
	int getGameRange();
	
	int getHareDemandedPositionUpdateInterval();
	int getHoundsDemandedPositionUpdateInterval();
	
	int getDemandedInitialHarePositions();
}